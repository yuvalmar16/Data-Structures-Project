public class FacultyData  implements Comparable<FacultyData> {
    private Faculty faculty;
    PlayersScore PlayersArray[]=new PlayersScore[11];
    int facultyPoints;



    public FacultyData(Faculty faculty) {
        this.faculty = faculty;
        this.facultyPoints=0;
    }

    public Faculty getFaculty() {
        return faculty;
    }
    public void SetfacultyPoints(int facultyPoints) {
        this.facultyPoints=facultyPoints;
    }


    @Override
    public int compareTo(FacultyData o) {
        if (this.faculty.getId()<o.faculty.getId())
            return -1;
        else if(this.faculty.getId()>o.faculty.getId())
            return 1;
        return 0;
    }

    @Override
    public int hashCode(){
        return getFaculty().getId();
    }
}
