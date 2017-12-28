package shaolizhi.sunshinebox.ui.index.nursery_rhymes;

import java.util.List;

/**
 * 由邵励治于2017/12/28创造.
 */

public class NurseryRhymesBean {

    /**
     * flag : 001
     * message : success
     * content : [{"course_id":"17","course_type":"music","course_name":"大黄蜂","course_video":"http://sunshinebox-1255613827.file.myqcloud.com/%E9%9F%B3%E4%B9%90/%E5%A4%A7%E9%BB%84%E8%9C%82/01%E5%A4%A7%E9%BB%84%E8%9C%82.mp4","course_audio":null,"last_modification_time":"1514293465"},{"course_id":"18","course_type":"music","course_name":"蜂房在这里","course_video":"http://sunshinebox-1255613827.file.myqcloud.com/%E9%9F%B3%E4%B9%90/%E8%9C%82%E6%88%BF%E5%9C%A8%E8%BF%99%E9%87%8C/01%E8%9C%82%E6%88%BF%E5%9C%A8%E8%BF%99%E9%87%8C.mp4","course_audio":"http://sunshinebox-1255613827.file.myqcloud.com/%E9%9F%B3%E4%B9%90/%E8%9C%82%E6%88%BF%E5%9C%A8%E8%BF%99%E9%87%8C/01%E8%9C%82%E6%88%BF%E5%9C%A8%E8%BF%99%E9%87%8C.mp3","last_modification_time":"1514293474"},{"course_id":"19","course_type":"music","course_name":"两只小小鸟","course_video":"http://sunshinebox-1255613827.file.myqcloud.com/%E9%9F%B3%E4%B9%90/%E4%B8%A4%E5%8F%AA%E5%B0%8F%E5%B0%8F%E9%B8%9F/01%E4%B8%A4%E5%8F%AA%E5%B0%8F%E5%B0%8F%E9%B8%9F%28A%E7%89%88%29.mp4","course_audio":null,"last_modification_time":"1514293482"}]
     */

    private String flag;
    private String message;
    private List<ContentBean> content;

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<ContentBean> getContent() {
        return content;
    }

    public void setContent(List<ContentBean> content) {
        this.content = content;
    }

    public static class ContentBean {
        /**
         * course_id : 17
         * course_type : music
         * course_name : 大黄蜂
         * course_video : http://sunshinebox-1255613827.file.myqcloud.com/%E9%9F%B3%E4%B9%90/%E5%A4%A7%E9%BB%84%E8%9C%82/01%E5%A4%A7%E9%BB%84%E8%9C%82.mp4
         * course_audio : null
         * last_modification_time : 1514293465
         */

        private String course_id;
        private String course_type;
        private String course_name;
        private String course_video;
        private String course_audio;
        private String last_modification_time;

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

        public String getCourse_name() {
            return course_name;
        }

        public void setCourse_name(String course_name) {
            this.course_name = course_name;
        }

        public String getCourse_video() {
            return course_video;
        }

        public void setCourse_video(String course_video) {
            this.course_video = course_video;
        }

        public String getCourse_audio() {
            return course_audio;
        }

        public void setCourse_audio(String course_audio) {
            this.course_audio = course_audio;
        }

        public String getLast_modification_time() {
            return last_modification_time;
        }

        public void setLast_modification_time(String last_modification_time) {
            this.last_modification_time = last_modification_time;
        }
    }
}
