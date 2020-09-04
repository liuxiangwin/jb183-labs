package com.redhat.training.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.faces.bean.RequestScoped;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Inject;
import javax.inject.Named;

import com.redhat.training.ejb.ClassBean;
import com.redhat.training.model.Classroom;
import com.redhat.training.model.Student;
import com.redhat.training.model.Teacher;

@Named("classroomView")
@Stateless
@RequestScoped
public class ClassroomViewBean {
	
	private List<Classroom> classrooms;

	private Classroom currentClassroom;
	
	private Teacher teacher;
	
	private List<Student> students;
	
	@Inject
	private Logger log;

	@Inject
	ClassBean classBean;
	
	@PostConstruct
	public void init() {
		log.info("INIT!!!");
		classrooms = classBean.getAllClassrooms();
		
	}

	public void update(ValueChangeEvent event) {
		
		log.info("Attempting to match: "+ event.getNewValue());
				
		for(Classroom classroom: classrooms) {
			if(classroom.equals(event.getNewValue())) {
				teacher = classroom.getTeacher();
				students = new ArrayList<Student>(classroom.getStudents());
				log.info("Match!");
				break;
			}
		}
		
		
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public List<Classroom> getClassrooms() {
		return classrooms;
	}

	public void setClassrooms(List<Classroom> classrooms) {
		this.classrooms = classrooms;
	}

	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}


	public Classroom getCurrentClassroom() {
		return currentClassroom;
	}

	public void setCurrentClassroom(Classroom currentClassroom) {
		this.currentClassroom = currentClassroom;
	}

	

}
