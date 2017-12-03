package shaolizhi.sunshinebox.ui.activation_code_verify;

import java.util.List;

/**
 * 由邵励治于2017/11/30创造.
 */

public class ActivationCodeVerifyBean {

    /**
     * flag : 001
     * message : success
     * content : {"uuid":[{"uuid":"1"}]}
     */

    private String flag;
    private String message;
    private ContentBean content;

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

    public ContentBean getContent() {
        return content;
    }

    public void setContent(ContentBean content) {
        this.content = content;
    }

    public static class ContentBean {
        private List<UuidBean> uuid;

        public List<UuidBean> getUuid() {
            return uuid;
        }

        public void setUuid(List<UuidBean> uuid) {
            this.uuid = uuid;
        }

        public static class UuidBean {
            /**
             * uuid : 1
             */

            private String uuid;

            public String getUuid() {
                return uuid;
            }

            public void setUuid(String uuid) {
                this.uuid = uuid;
            }
        }
    }
}
