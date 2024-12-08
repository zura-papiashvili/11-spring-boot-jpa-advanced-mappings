package com.springcourse.cruddemo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.springcourse.cruddemo.dao.AppDAO;
import com.springcourse.cruddemo.entity.InstructorDetail;
import com.springcourse.cruddemo.entity.Instructor;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(AppDAO appDAO) {
		return runner -> {
			// createInstructor(appDAO);
			// findInsructorById(appDAO);
			// deleteInstructorById(appDAO);
			// updateInstructor(appDAO);
			// findInstructorDetailById(appDAO);
			deleteInstructorDetailById(appDAO);
		};
	};

	private void deleteInstructorDetailById(AppDAO appDAO) {
		appDAO.deleteInstructorDetailById(5);
		System.out.println("Deleted");

	}

	private void findInstructorDetailById(AppDAO appDAO) {
		InstructorDetail instructorDetail = appDAO.findInstructorDetailById(1);
		System.err.println();
		System.out.println(instructorDetail);
		System.err.println();
		System.out.println(instructorDetail.getInstructor());
	}

	private void createInstructor(AppDAO appDAO) {
		Instructor instructor = new Instructor("Chad2", "Darby2", "cf2@gmail.com");

		InstructorDetail instructorDetail = new InstructorDetail("http://www.luv2code.com/youtube", "Luv 2 code!!!");

		instructor.setInstructorDetail(instructorDetail);

		appDAO.save(instructor);

	}

	private void findInsructorById(AppDAO appDAO) {
		Instructor instructor = appDAO.findById(1);
		System.err.println();
		System.out.println(instructor);
		System.err.println();
	}

	private void deleteInstructorById(AppDAO appDAO) {
		appDAO.deleteById(4);

		System.out.println("Deleted");
	}

	private void updateInstructor(AppDAO appDAO) {
		// Retrieve the instructor by id (assuming id is 1)
		int instructorId = 2;
		Instructor instructor = appDAO.findById(instructorId);
		System.out.println("Instructor: " + instructor);
		// Check if instructor exists
		if (instructor != null) {
			// Update the instructor's details
			instructor.setFirstName("2UpdatedFirstName");
			instructor.setLastName("2UpdatedLastName");
			instructor.setEmail("updatedemail@example.com");

			// Save the updated instructor
			appDAO.update(instructor);
		} else {
			System.out.println("Instructor with id " + instructorId + " not found.");
		}
	}

}
