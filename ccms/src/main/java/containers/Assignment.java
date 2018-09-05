package containers;

public class Assignment {
    private String id;
    private String url;
    private Integer grade;
    private Boolean isFinished;


        public Assignment(String id){
            this.id = id;
        }

        public Assignment(String id, String url, Integer grade, boolean isFinished) {
            this.id = id;
            this.url = url;
            this.grade = grade;
            this.isFinished = isFinished;
        }


        public void setUrl(String url){
            this.url = url;
        }


        public void setGrade(Integer grade){
            this.grade = grade;
        }


        public void setIsFinished(Boolean isFinished){
            this.isFinished = isFinished;
        }


        public String getId(){
            return this.id;
        }


        public String getUrl(){
            return this.url;
        }


        public Integer getGrade(){
            return this.grade;
        }


        public Boolean getIsFinished(){
            return this.isFinished;
        }

}
