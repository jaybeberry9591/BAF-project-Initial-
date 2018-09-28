package com.example.user.janina;

public class StudentInfo {


        String sname,sid,slevel,scredit;

        public StudentInfo(String sname, String sid, String slevel, String scredit) {
            this.sname = sname;
            this.sid = sid;
            this.slevel = slevel;
            this.scredit = scredit;
        }

        public String getSname() {
            return sname;
        }

        public void setSname(String sname) {
            this.sname = sname;
        }

        public String getSid() {
            return sid;
        }

        public void setSid(String sid) {
            this.sid = sid;
        }

        public String getSlevel() {
            return slevel;
        }

        public void setSlevel(String slevel) {
            this.slevel = slevel;
        }

        public String getScredit() {
            return scredit;
        }

        public void setScredit(String scredit) {
            this.scredit = scredit;
        }


}
