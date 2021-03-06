package com.tolgaduran.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.tolgaduran.demo.entity.Course;
import com.tolgaduran.demo.entity.Instructor;
import com.tolgaduran.demo.entity.InstructorDetail;
import com.tolgaduran.demo.entity.Review;

public class DeleteCourseAndReviewsDemo {

	public static void main(String[] args) {
		SessionFactory factory= new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.addAnnotatedClass(Course.class)
				.addAnnotatedClass(Review.class)
				.buildSessionFactory();

		Session session=factory.getCurrentSession();

		try {
			// start a transaction
			session.beginTransaction();

			// get the course
			int theId=10;
			Course tempCourse=session.get(Course.class, theId);
			
			// print the course
			System.out.println("Deleting the course");
			System.out.println(tempCourse);
			
			// print the course reviews
			System.out.println(tempCourse.getReviews());
			
			
			// delete the course
			session.delete(tempCourse);
			
			// commit transaction
			session.getTransaction().commit();
			
			System.out.println("Done!");
		} finally {
			session.close();
			factory.close();
		}
	}
}
