package shaolizhi.sunshinebox.ui.course;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

import shaolizhi.sunshinebox.R;
import shaolizhi.sunshinebox.objectbox.courses.Courses;
import shaolizhi.sunshinebox.utils.IOUtils;

/**
 * 由邵励治于2018/1/4创造.
 */

public class CoursePresenter implements CourseContract.Presenter, CourseContract.CallBack {

    private CourseContract.View view;

    private CourseContract.Model model;

    private String courseId = null;

    private Boolean isAudioDownloading = false;

    private Boolean isVideoDownloading = false;

    CoursePresenter(CourseContract.View view) {
        this.view = view;
        model = new CourseModel(this, view.getActivity());
        try {
            courseId = view.getCourseIdFromIntent();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void start() {
        view.setUpView();
        setUpButtonTextWhenThereIsNoDownload();
    }

    private void setUpButtonTextWhenThereIsNoDownload() {
        Courses courses = null;
        if (courseId != null) {
            courses = model.getCourseByCourseId(courseId);
        }

        if (courses != null) {
            if (courses.getIs_audio_downloaded()) {
                if (courses.getCourse_audio() == null) {
                    view.setAudioButtonText(R.string.course_act_string12);
                    view.setAudioButtonEnable(false);
                } else {
                    view.setAudioButtonText(R.string.course_act_string2);
                    view.setAudioButtonEnable(true);
                }
            } else {
                if (courses.getCourse_audio() == null) {
                    view.setAudioButtonText(R.string.course_act_string12);
                    view.setAudioButtonEnable(false);
                } else {
                    view.setAudioButtonText(R.string.course_act_string6);
                    view.setAudioButtonEnable(true);
                }
            }

            if (courses.getIs_video_downloaded()) {
                if (courses.getCourse_video() == null) {
                    view.setVideoButtonText(R.string.course_act_string13);
                    view.setVideoButtonEnable(false);
                } else {
                    view.setVideoButtonText(R.string.course_act_string3);
                    view.setVideoButtonEnable(true);
                }
            } else {
                if (courses.getCourse_video() == null) {
                    view.setVideoButtonText(R.string.course_act_string13);
                    view.setVideoButtonEnable(false);
                } else {
                    view.setVideoButtonText(R.string.course_act_string7);
                    view.setVideoButtonEnable(true);
                }
            }
        }
    }

    @Override
    public void tryToPlayAudio() {
        if (isAudioDownloading) {
            Snackbar.make(view.getCoordinatorLayout(), R.string.course_act_string11, Snackbar.LENGTH_SHORT).show();
        } else {
            Courses courses = null;
            if (courseId != null) {
                courses = model.getCourseByCourseId(courseId);
            }

            if (courses != null) {
                if (courses.getIs_audio_downloaded()) {
                    //音频已下载
                    playAudio();
                } else {
                    //请求权限
                    checkPermissions();
                    //音频未下载
                    downloadAudio();
                }
            }
        }

    }

    private void playAudio() {
        Courses courses = model.getCourseByCourseId(courseId);
        if (courses != null) {
            Uri uri = Uri.parse(courses.getAudio_storage_address());
            Toast.makeText(view.getActivity(), String.valueOf(uri), Toast.LENGTH_LONG).show();
        }
    }

    private void downloadAudio() {
        isAudioDownloading = true;
        //弹出SnackBar
        Snackbar.make(view.getCoordinatorLayout(), R.string.course_act_string8, Snackbar.LENGTH_SHORT).show();
        IOUtils.createDirectory();
        model.requestAudioByCourseId(courseId);
    }

    @Override
    public void tryToPlayVideo() {
        if (isVideoDownloading) {
            Snackbar.make(view.getCoordinatorLayout(), R.string.course_act_string10, Snackbar.LENGTH_SHORT).show();
        } else {
            Courses courses = null;
            if (courseId != null) {
                courses = model.getCourseByCourseId(courseId);
            }
            if (courses != null) {
                if (courses.getIs_video_downloaded()) {
                    //视频已下载
                    playVideo();
                } else {
                    //请求权限
                    checkPermissions();
                    //视频未下载
                    downloadVideo();
                }
            }
        }
    }

    @Override
    public void updateAudioDownloadProgress(Long percent) {
        view.setAudioButtonText(String.valueOf(percent) + "%");
    }

    @Override
    public void updateVideoDownloadProgress(Long percent) {
        view.setVideoButtonText(String.valueOf(percent) + "%");
    }

    private void playVideo() {
        Courses courses = model.getCourseByCourseId(courseId);
        if (courses != null) {
            Uri uri = Uri.parse(courses.getVideo_storage_address());
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setDataAndType(uri, "video/mp4");
            view.getActivity().startActivity(intent);
        }
    }

    private void downloadVideo() {
        isVideoDownloading = true;
        //弹出SnackBar
        Snackbar.make(view.getCoordinatorLayout(), R.string.course_act_string8, Snackbar.LENGTH_SHORT).show();
        IOUtils.createDirectory();
        model.requestVideoByCourseId(courseId);
    }

    private void checkPermissions() {
        //检查运行时权限
        if (ContextCompat.checkSelfPermission(view.getActivity(), Manifest.permission.MOUNT_UNMOUNT_FILESYSTEMS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(view.getActivity(), new String[]{Manifest.permission.MOUNT_UNMOUNT_FILESYSTEMS}, 1);
        }

        if (ContextCompat.checkSelfPermission(view.getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(view.getActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 2);
        }
    }

    @Override
    public void downloadVideoSuccess() {
        isVideoDownloading = false;
        Snackbar.make(view.getCoordinatorLayout(), R.string.course_act_string9, Snackbar.LENGTH_SHORT).show();
        view.setVideoButtonText(R.string.course_act_string3);
    }

    @Override
    public void downloadAudioSuccess() {
        isAudioDownloading = false;
        Snackbar.make(view.getCoordinatorLayout(), R.string.course_act_string14, Snackbar.LENGTH_SHORT).show();
        view.setAudioButtonText(R.string.course_act_string2);
    }
}
