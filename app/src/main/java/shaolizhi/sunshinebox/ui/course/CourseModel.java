package shaolizhi.sunshinebox.ui.course;

import android.app.Activity;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.ref.WeakReference;

import io.objectbox.Box;
import io.objectbox.query.Query;
import io.objectbox.query.QueryBuilder;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import shaolizhi.sunshinebox.data.ApiService;
import shaolizhi.sunshinebox.data.ConstantData;
import shaolizhi.sunshinebox.objectbox.courses.Courses;
import shaolizhi.sunshinebox.objectbox.courses.CoursesUtils;
import shaolizhi.sunshinebox.objectbox.courses.Courses_;
import shaolizhi.sunshinebox.utils.ServiceGenerator;

/**
 * 由邵励治于2018/1/4创造.
 */

class CourseModel implements CourseContract.Model {

    private static final String TAG = "CourseActivity";

    private final ApiService apiService = ServiceGenerator.createService(ApiService.class);

    private CourseContract.CallBack callBack;

    private Box<Courses> coursesBox;

    CourseModel(@NonNull CourseContract.CallBack callBack, @NonNull Activity activity) {
        this.callBack = callBack;
        //get courses-box
        coursesBox = CoursesUtils.getInstance().getCoursesBox(activity);
    }

    @Override
    public Courses getCourseByCourseId(String courseId) {
        QueryBuilder<Courses> builder = coursesBox.query();
        Query<Courses> query = builder.equal(Courses_.course_id, courseId).build();
        return query.findUnique();
    }

    public enum MediaType {
        MP3, MP4
    }

    private File getStorageAddress(String courseType, String courseId, MediaType audioOrVideo) {
        File fileDownloaded = null;
        String mediaType = null;

        switch (audioOrVideo) {
            case MP3:
                mediaType = ".mp3";
                break;
            case MP4:
                mediaType = ".mp4";
                break;
        }

        switch (courseType) {
            case "music":
                fileDownloaded = new File(ConstantData.MUSIC_DIRECTORY + File.separator + courseId + mediaType);
                break;
            case "reading":
                fileDownloaded = new File(ConstantData.READING_DIRECTORY + File.separator + courseId + mediaType);
                break;
            case "rhymes":
                fileDownloaded = new File(ConstantData.RHYMES_DIRECTORY + File.separator + courseId + mediaType);
                break;
            case "game":
                fileDownloaded = new File(ConstantData.GAME_DIRECTORY + File.separator + courseId + mediaType);
                break;
            default:
                Log.e(TAG, "CourseModel's writeResponseBodyToDisk fucked. Because courseType input wrong.");
                break;
        }
        return fileDownloaded;
    }


    @Override
    public void requestVideoByCourseId(final String courseId) {
        final Courses courses;
        courses = getCourseByCourseId(courseId);
        if (courses != null) {
            Call<ResponseBody> call = apiService.downloadFileWithDynamicUrl(courses.getCourse_video());
            Log.e(TAG, "video url:" + courses.getCourse_video());
            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(@NonNull Call<ResponseBody> call, @NonNull final Response<ResponseBody> response) {
                    if (response.isSuccessful()) {
                        Log.e(TAG, "server contacted and has file");
                        DownloadFile downloadFile = new DownloadFile(CourseModel.this, courseId, response, MediaType.MP4);
                        downloadFile.execute();
                    } else {
                        Log.e(TAG, "server contact failed");
                    }
                }

                @Override
                public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
                    callBack.downloadVideoFailure();
                }
            });
        }
    }

    private static class DownloadFile extends AsyncTask<Void, Long, Void> {

        private WeakReference<CourseModel> courseModelWeakReference;

        private String courseId;

        private Response<ResponseBody> response;

        private Boolean isFileDownloadSuccess = false;

        private File file = null;

        private MediaType mediaType;

        DownloadFile(CourseModel courseModel, String courseId, Response<ResponseBody> response, MediaType mediaType) {
            this.courseModelWeakReference = new WeakReference<>(courseModel);
            this.courseId = courseId;
            this.response = response;
            this.mediaType = mediaType;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            CourseModel courseModel = courseModelWeakReference.get();

            if (courseModel != null) {
                //下载文件
                Courses courses = courseModel.getCourseByCourseId(courseId);
                file = courseModel.getStorageAddress(courses.getCourse_type(), courses.getCourse_id(), mediaType);
                isFileDownloadSuccess = writeResponseBodyToDisk(response.body(), file);
                Log.i(TAG, "file download was a success?" + isFileDownloadSuccess);
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            CourseModel courseModel = courseModelWeakReference.get();
            if (courseModel != null) {
                if (isFileDownloadSuccess) {
                    Courses courses = courseModel.getCourseByCourseId(courseId);
                    switch (mediaType) {
                        case MP3:
                            if (file != null) {
                                courses.setIs_audio_downloaded(true);
                                courses.setAudio_storage_address(file.getAbsolutePath());
                                courseModel.coursesBox.put(courses);
                            }
                            courseModel.callBack.downloadAudioSuccess();
                            break;
                        case MP4:
                            if (file != null) {

                                courses.setIs_video_downloaded(true);
                                courses.setVideo_storage_address(file.getAbsolutePath());
                                courseModel.coursesBox.put(courses);
                            }
                            courseModel.callBack.downloadVideoSuccess();
                            break;
                    }

                }
            }
            super.onPostExecute(aVoid);
        }

        @Override
        protected void onProgressUpdate(Long... values) {
            super.onProgressUpdate(values);
            CourseModel courseModel = courseModelWeakReference.get();
            if (courseModel != null) {
                Long progress = values[0];
                if (progress != null) {
                    switch (mediaType) {
                        case MP4:
                            courseModel.callBack.updateVideoDownloadProgress(progress);
                            break;
                        case MP3:
                            courseModel.callBack.updateAudioDownloadProgress(progress);
                            break;
                    }

                }
            }
        }

        private boolean writeResponseBodyToDisk(ResponseBody body, File file) {
            try {
                // todo change the file location/name according to your needs
                InputStream inputStream = null;
                OutputStream outputStream = null;

                try {
                    byte[] fileReader = new byte[4096];

                    long fileSize = body.contentLength();
                    long fileSizeDownloaded = 0;

                    inputStream = body.byteStream();
                    outputStream = new FileOutputStream(file);

                    while (true) {
                        int read = inputStream.read(fileReader);

                        if (read == -1) {
                            break;
                        }
                        Log.e(TAG, "read:" + String.valueOf(read));
                        outputStream.write(fileReader, 0, read);

                        fileSizeDownloaded += read;

                        Log.e(TAG, "file download: " + fileSizeDownloaded + " of " + fileSize);
                        float downloadPercent = (float) fileSizeDownloaded / (float) fileSize;
                        long result = (long) (downloadPercent * 100);
                        publishProgress(result);
                    }

                    outputStream.flush();

                    return true;
                } catch (IOException e) {
                    return false;
                } finally {
                    if (inputStream != null) {
                        inputStream.close();
                    }

                    if (outputStream != null) {
                        outputStream.close();
                    }
                }
            } catch (IOException e) {
                return false;
            }
        }

    }

    @Override
    public void requestAudioByCourseId(final String courseId) {
        final Courses courses;
        courses = getCourseByCourseId(courseId);
        if (courses != null) {
            Call<ResponseBody> call = apiService.downloadFileWithDynamicUrl(courses.getCourse_audio());
            Log.e(TAG, "audio url:" + courses.getCourse_audio());
            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(@NonNull Call<ResponseBody> call, @NonNull final Response<ResponseBody> response) {
                    if (response.isSuccessful()) {
                        Log.e(TAG, "server contacted and has file");
                        DownloadFile downloadFile = new DownloadFile(CourseModel.this, courseId, response, MediaType.MP3);
                        downloadFile.execute();
                    } else {
                        Log.e(TAG, "server contact failed");
                    }
                }

                @Override
                public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
                    callBack.downloadAudioFailure();
                }
            });
        }
    }
}
