package com.citonline.domain;

import java.util.ArrayList;

import org.springframework.web.multipart.MultipartFile;
import com.citonline.interfaces.impl.ModuleImpl;
/**
 * 
 * @author peter halligan
 * deferral object
 * holds information relating to the deferral including student id, and Arraylist of deferred modules
 *
 */
public class Deferral 
{
	int id;
	String deferral_date;
	int id_student;
	ArrayList<ModuleImpl> deferredModules;
	boolean programDeferred;
	int id_program;
	int id_deferral_status;
	private MultipartFile file;
	
	/**
	 * costructor with id for row mapper
	 * @param id
	 * @param deferral_date
	 * @param id_student
	 * @param id_program
	 * @param program_deferred
	 * @param id_deferral_status
	 */
	public Deferral(int id, String deferral_date, int id_student,int id_program, boolean program_deferred, int id_deferral_status) {
		super();
		this.id=id;
		this.deferral_date = deferral_date;
		this.id_student = id_student;
		this.programDeferred = program_deferred;
		this.id_program = id_program;
		this.id_deferral_status =id_deferral_status;
		deferredModules = new ArrayList<ModuleImpl>();
	}
	/**
	 * constructor with no id 
	 * @param deferral_date
	 * @param id_student
	 * @param id_program
	 * @param program_deferred
	 * @param id_deferral_status
	 */
	public Deferral(String deferral_date, int id_student,int id_program, boolean program_deferred, int id_deferral_status) {
		super();
		this.deferral_date = deferral_date;
		this.id_student = id_student;
		this.programDeferred = program_deferred;
		this.id_program = id_program;
		this.id_deferral_status =id_deferral_status;
		deferredModules = new ArrayList<ModuleImpl>();
	}
	public Deferral() {}
	public int getId_deferral_status() {
		return id_deferral_status;
	}

	public void setId_deferral_status(int status) {
		this.id_deferral_status = status;
	}
	public boolean getProgramDeferred() {
		return programDeferred;
	}

	public void setProgramDeffered(boolean programDeffered) {
		this.programDeferred = programDeffered;
	}
	
	public int getId_program() {
		return id_program;
	}
	public void setId_program(int id_program) {
		this.id_program = id_program;
	}
	
	public String getDeferral_date() {
		return deferral_date;
	}
	public void setDeferral_date(String deferral_date) {
		this.deferral_date = deferral_date;
	}
	public ArrayList<ModuleImpl> getDeferredModules() {
		return deferredModules;
	}
	public void setDeferredModules(ArrayList<ModuleImpl> deferredModules) {
		this.deferredModules = deferredModules;
	}
	public void setProgramDeferred(boolean programDeferred) {
		this.programDeferred = programDeferred;
	}
	public int getId_student() {
		return id_student;
	}
	public void setId_student(int id_student) {
		this.id_student = id_student;
	}
	public ArrayList<ModuleImpl> getDefferedModules() {
		return deferredModules;
	}
	public void setDefferedModules(ArrayList<ModuleImpl> defferedModules) {
		this.deferredModules = defferedModules;
	}

	public int getId() {
		return id;
	}

	public void setId(int id_deferral) {
		this.id= id_deferral;
	}
	
	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}
	
}
