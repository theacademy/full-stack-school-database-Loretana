package mthree.com.fullstackschool.service;

import mthree.com.fullstackschool.dao.CourseDao;
import mthree.com.fullstackschool.model.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseServiceInterface {

    //YOUR CODE STARTS HERE
    private CourseDao courseDao;

    @Autowired
    public CourseServiceImpl(CourseDao courseDao) {
        this.courseDao = courseDao;
    }

    //YOUR CODE ENDS HERE

    public List<Course> getAllCourses() {
        //YOUR CODE STARTS HERE

        return new ArrayList<>(courseDao.getAllCourses());

        //YOUR CODE ENDS HERE
    }

    public Course getCourseById(int id) {
        //YOUR CODE STARTS HERE
        try {
            return courseDao.findCourseById(id);
        } catch(DataAccessException ex) {
            Course courseNotFound = new Course();
            courseNotFound.setCourseName("Course Not Found");
            courseNotFound.setCourseDesc("Course Not Found");
            return courseNotFound;
        }

        //YOUR CODE ENDS HERE
    }

    public Course addNewCourse(Course course) {
        //YOUR CODE STARTS HERE
        return courseDao.createNewCourse(course);

        //YOUR CODE ENDS HERE
    }

    public Course updateCourseData(int id, Course course) {
        //YOUR CODE STARTS HERE
        if(id == course.getCourseId()) {
            courseDao.updateCourse(course);
            try {
                return courseDao.findCourseById(id);
            } catch (DataAccessException ex) {
                Course courseNotFound = new Course();
                courseNotFound.setCourseName("Course Not Found");
                courseNotFound.setCourseDesc("Course Not Found");
                return courseNotFound;
            }
        } else {
            course.setCourseName("IDs do not match, course not updated");
            course.setCourseDesc("IDs do not match, course not updated");
            return course;
        }


        //YOUR CODE ENDS HERE
    }

    public void deleteCourseById(int id) {
        //YOUR CODE STARTS HERE
        courseDao.deleteCourse(id);
        //YOUR CODE ENDS HERE
    }
}
