package shaolizhi.sunshinebox.ui.index;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.util.Log;

import java.util.List;

import io.objectbox.Box;
import io.objectbox.query.Query;
import io.objectbox.query.QueryBuilder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import shaolizhi.sunshinebox.data.ApiService;
import shaolizhi.sunshinebox.objectbox.courses.Courses;
import shaolizhi.sunshinebox.objectbox.courses.CoursesUtils;
import shaolizhi.sunshinebox.objectbox.courses.Courses_;
import shaolizhi.sunshinebox.utils.ServiceGenerator;

/**
 * 由邵励治于2017/12/27创造.
 */

public class IndexModel implements IndexContract.Model {

    private IndexContract.CallBack callBack;

    private CoursesUtils coursesUtils;

    private Box<Courses> coursesBox;

    IndexModel(@NonNull IndexContract.CallBack callBack, @NonNull Activity activity) {
        this.callBack = callBack;
        //get coursesUtils
        coursesUtils = CoursesUtils.getInstance();
        //get courses-box
        coursesBox = coursesUtils.getCoursesBox(activity);
    }

    @Override
    public Boolean isThereAnyDataInTheDatabase() {
        return coursesUtils.isCoursesBoxHasData(coursesBox);
    }

    @Override
    public void requestDataFromNet(@NonNull String courseType, @NonNull String maxLastModificationTime) {
        ApiService apiService = ServiceGenerator.createService(ApiService.class);
        Call<IndexBean> call = apiService.getIndexDataAPI(courseType, maxLastModificationTime);
        call.enqueue(new Callback<IndexBean>() {
            @Override
            public void onResponse(@NonNull Call<IndexBean> call, @NonNull Response<IndexBean> response) {
                IndexBean bean = response.body();
                if (bean != null) {
                    callBack.requestDataFromNetSuccess(bean);
                }
            }

            @Override
            public void onFailure(@NonNull Call<IndexBean> call, @NonNull Throwable t) {
                callBack.requestDataFromNetFailure();
            }
        });
    }

    @Override
    public List requestDataFromDatabase(@NonNull String courseType) {
        QueryBuilder<Courses> builder = coursesBox.query();
        Query query = builder.equal(Courses_.course_type, courseType).build();
        return query.find();
    }

    /**
     * 将网络中的数据存储到数据库中，
     * 已存在的 UPDATE
     * 不存在的 INSERT
     *
     * @param bean 传入的网络中的数据
     */
    @Override
    public void storedInTheDatabase(@NonNull IndexBean bean) {
        List<IndexBean.ContentBean> contentBeans = bean.getContent();
        for (IndexBean.ContentBean item : contentBeans) {
            Long id = getIdFromDatabase(item.getCourse_id());
            Courses courses = new Courses();
            courses.setId(id);
            courses.setCourse_name(item.getCourse_name());
            courses.setCourse_id(item.getCourse_id());
            courses.setCourse_type(item.getCourse_type());
            courses.setCourse_video(item.getCourse_video());
            courses.setCourse_audio(item.getCourse_audio());
            courses.setLast_modification_time(Long.parseLong(item.getLast_modification_time()));
            courses.setVideo_storage_address(null);
            courses.setAudio_storage_address(null);
            courses.setDownload(false);
            coursesBox.put(courses);
            if (id == 0) {
                Log.e("ObjectBox", "Inserted new courses, ID:" + courses.getId());
            } else {
                Log.e("ObjectBox", "Updated a courses, ID:" + courses.getId());
            }
        }
    }

    /**
     * 查询course_id是否在数据库中存在
     *
     * @param courseId 要查询的course_id
     * @return 若存在返回Id，若不存在返回0
     */
    private long getIdFromDatabase(@NonNull String courseId) {
        QueryBuilder<Courses> builder = coursesBox.query();
        Query query = builder.equal(Courses_.course_id, courseId).build();
        List coursesList = query.find();
        if (coursesList.size() == 0) {
            return 0;
        } else {
            Object object = coursesList.get(0);
            if (object instanceof Courses) {
                return ((Courses) object).getId();
            }
        }
        return 0;
    }

    @Override
    public String getMaxModificationTime() {
        QueryBuilder<Courses> builder = coursesBox.query();
        Query query = builder.build();
        return String.valueOf(query.max(Courses_.last_modification_time));
    }
}
