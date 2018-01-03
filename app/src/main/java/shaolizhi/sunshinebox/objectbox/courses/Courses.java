package shaolizhi.sunshinebox.objectbox.courses;

import java.io.Serializable;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;

/**
 * 由邵励治于2017/12/27创造.
 */

@Entity
public class Courses implements Serializable{
    @Id
    long id;

    String course_id;

    String course_type;

    String course_video;

    String course_audio;

    String course_name;

    Long last_modification_time;

    String video_storage_address;

    String audio_storage_address;

    Boolean isDownload;

    public String getCourse_audio() {
        return course_audio;
    }

    public void setCourse_audio(String course_audio) {
        this.course_audio = course_audio;
    }

    @Override
    public String toString() {
        return "Courses{" +
                "id=" + id +
                ", course_id='" + course_id + '\'' +
                ", course_type='" + course_type + '\'' +
                ", course_video='" + course_video + '\'' +
                ", course_name='" + course_name + '\'' +
                ", last_modification_time='" + last_modification_time + '\'' +
                ", video_storage_address='" + video_storage_address + '\'' +
                ", audio_storage_address='" + audio_storage_address + '\'' +
                ", isDownload=" + isDownload +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCourse_id() {
        return course_id;
    }

    public void setCourse_id(String course_id) {
        this.course_id = course_id;
    }

    public String getCourse_type() {
        return course_type;
    }

    public void setCourse_type(String course_type) {
        this.course_type = course_type;
    }

    public String getCourse_video() {
        return course_video;
    }

    public void setCourse_video(String course_video) {
        this.course_video = course_video;
    }

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public Long getLast_modification_time() {
        return last_modification_time;
    }

    public void setLast_modification_time(Long last_modification_time) {
        this.last_modification_time = last_modification_time;
    }

    public String getVideo_storage_address() {
        return video_storage_address;
    }

    public void setVideo_storage_address(String video_storage_address) {
        this.video_storage_address = video_storage_address;
    }

    public String getAudio_storage_address() {
        return audio_storage_address;
    }

    public void setAudio_storage_address(String audio_storage_address) {
        this.audio_storage_address = audio_storage_address;
    }

    public Boolean getDownload() {
        return isDownload;
    }

    public void setDownload(Boolean download) {
        isDownload = download;
    }
}
