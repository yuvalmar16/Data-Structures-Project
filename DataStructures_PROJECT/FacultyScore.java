public class FacultyScore implements Comparable<FacultyScore> {


    private Faculty faculty;
    int facultyPoints;
    FacultyScore next;
    FacultyScore prev;

    public FacultyScore(Faculty faculty, int facultyPoints) {
        this.faculty = faculty;
        this.facultyPoints = facultyPoints;


    }


    public Faculty getFaculty() {
        return faculty;
    }

    @Override
    public int compareTo(FacultyScore o) {
        if (this.facultyPoints == o.facultyPoints && this.getFaculty().getId() == o.getFaculty().getId()) {
            return 0;
        }
        if (this.facultyPoints == o.facultyPoints) {
            if (this.getFaculty().getId() < o.getFaculty().getId())
                return 1;
            else
                return -1;
        } else if (this.facultyPoints > o.facultyPoints) {
            return 1;
        }

            return -1;
        }



        public void updateScore(FacultyData faculty,int scoreToAdd){

        FacultyScore facultyScore = new FacultyScore(faculty.getFaculty(),faculty.facultyPoints);

        }

    }







