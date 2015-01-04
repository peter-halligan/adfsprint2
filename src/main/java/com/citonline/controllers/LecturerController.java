/**
 * @author Fabien
 *
 * @since 4 d�c. 2014
 */
package com.citonline.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.citonline.db.interfaces.LecturerDAO;
import com.citonline.interfaces.impl.LecturerImpl;


/**
 * @author Fabien
 *
 */
@Controller
@RequestMapping("/lecturer")
public class LecturerController {
	
	@Autowired
	LecturerDAO lecturerDAO;
	
	@RequestMapping(value={"/listall", "/listAll"}, method = RequestMethod.GET)
	public String listAll(ModelMap model) {
			
			List<LecturerImpl> listLecturers=lecturerDAO.listLecturers();
			model.addAttribute("lecturers", listLecturers);
		    return "displayLecturers";
		}
	
	@RequestMapping(value="/list/{firstName}/{lastName}", method=RequestMethod.GET)
	 public String listLecturerByFirstnameLastname(@PathVariable String firstName, @PathVariable String lastName,
			 ModelMap model){
		LecturerImpl lecturer=lecturerDAO.getLecturer(firstName, lastName);
		List<LecturerImpl> listLecturers = new ArrayList<LecturerImpl>();
		listLecturers.add(lecturer);
		
		model.addAttribute("lecturers", listLecturers);
	    return "displayLecturers";
	}
	
	@RequestMapping(value="/list/{id}", method=RequestMethod.GET)
	public String listLecturerByID(@PathVariable int id, ModelMap model){
		List<LecturerImpl> lecturers=new ArrayList<LecturerImpl>();
		LecturerImpl lecturer=lecturerDAO.getLecturer(id);
		lecturers.add(lecturer);
		model.addAttribute("lecturers", lecturers);
	    return "displayLecturers";
	}
	
	@RequestMapping(value = "/addNew", method = RequestMethod.GET) 
	public String addNewLecturer(ModelMap model) {    
		model.addAttribute("lecturer", new LecturerImpl());	
		return "newLecturer";
	} 

	/*
	@RequestMapping(value = "/addNew", method = RequestMethod.POST)
	public String displayLecturer(@ModelAttribute("lecturer") @Valid LecturerImpl lecturer,
			@RequestParam("file") MultipartFile file,
			BindingResult result, ModelMap model) {
		
		if(result.hasErrors())
			return "newLecturer"; 
		
		try {
			 if (!file.isEmpty()) {
				 
				 validateImage(file);
				 
		            try {     
		            	model.addAttribute("firstName", lecturer.getFirstName());
			        	model.addAttribute("lastName", lecturer.getLastName());
			        	model.addAttribute("email", lecturer.getEmail());
			        	model.addAttribute("phoneNumber", lecturer.getPhoneNumber());
			        	model.addAttribute("roomNumber", lecturer.getRoomNumber());
			        	model.addAttribute("idManagedProgram", lecturer.getIdManagedProgram());
			        	int id= lecturerDAO.createLecturerGetID(lecturer.getFirstName(), lecturer.getLastName(), lecturer.getEmail(),
			        			lecturer.getPhoneNumber(), lecturer.getRoomNumber(), lecturer.getIdManagedProgram());
			        	model.addAttribute("id", Integer.toString(id));
		            	
		                byte[] bytes = file.getBytes(); 
		                File dir = new File(servletContext.getRealPath("/")+"/resources/images");
		                System.out.println(dir.getAbsolutePath());
		                
		                if (!dir.exists())
		                    dir.mkdirs();		                
		               		 
		                // Create the file on server
		                String fileLocation=dir.getAbsolutePath()
		                        + File.separator + Integer.toString(id)+".jpg";;
		                
		                File serverFile = new File(fileLocation);
		                
		                BufferedOutputStream stream = new BufferedOutputStream(
		                        new FileOutputStream(serverFile));
		                
		                stream.write(bytes);
		                stream.close();		  
		    			
		            } catch (Exception e) {
		            	model.addAttribute("message", "Creation of lecturer failed, "+e.getLocalizedMessage());
						return "displayError"; 

		            }
			 }else{
				 model.addAttribute("message", "You failed to upload " + file.getOriginalFilename() + " because the file was empty.");
				 return "displayError"; 
			 }
			} catch(ImageUploadException e){
				 model.addAttribute("message", "Creation of lecturer failed. The system only supports JPEGs.");
				 return "displayError"; 
			
			}		
		
		return "displayLecturer";

	}
	*/
	
	@RequestMapping(value="/modify", method = RequestMethod.GET)
	public String modify(ModelMap model) {
		List<LecturerImpl> lecturers=lecturerDAO.listLecturers();
		model.addAttribute("lecturers", lecturers);
		return "modifyLecturer";
	}
	
	@RequestMapping(value ="/modify/id/{id}", method = RequestMethod.GET)
	public String modifyLecturerByID(@PathVariable int id, ModelMap model) {
		LecturerImpl lecturerModify=lecturerDAO.getLecturer(id);
		model.addAttribute("message", "Lecturer with id "+ id +" can now be modified");
		model.addAttribute("lecturer", lecturerModify);
		return "modifyLecturerForm";
	}
	
	@RequestMapping(value ="/modify/firstName/{firstName}/lastName/{lastName}", method = RequestMethod.GET)
	public String modifyLecturerByFirstNameLastName(@PathVariable String firstName, @PathVariable String lastName,
			ModelMap model) {
		LecturerImpl lecturerModify=lecturerDAO.getLecturer(firstName, lastName);
		model.addAttribute("message", "Lecturer with name "+ firstName + " " + lastName +" can now be modified");
		model.addAttribute("lecturer", lecturerModify);
		return "modifyLecturerForm";
	}
	
	@RequestMapping(value="/modify/id/{id}/idManagedProgram/{idManagedProgram}", method = RequestMethod.GET)
	public String modifyLecturerIdManagedProgram(@PathVariable int id, @PathVariable int idManagedProgram,
			ModelMap model) {
		lecturerDAO.updateLecturerManagedProgram(id, idManagedProgram);
		LecturerImpl lecturerModify=lecturerDAO.getLecturer(id);
		model.addAttribute("message", "Lecturer with id "+ id +" has been modified");
		model.addAttribute("firstName", lecturerModify.getFirstName());
		model.addAttribute("lastName", lecturerModify.getLastName());
		model.addAttribute("idManagedProgram", lecturerModify.getIdManagedProgram());
		return "displayLecturer";
	}
	
	@RequestMapping(value ="/modify/firstName/{firstName}/lastName/{lastName}/idManagedProgram/{idManagedProgram}",
			method = RequestMethod.GET)
	public String modifyLecturerIdManagedProgram(@PathVariable String firstName, @PathVariable String lastName,
			@PathVariable int idManagedProgram, ModelMap model) {
		lecturerDAO.updateLecturerManagedProgram(firstName, lastName, idManagedProgram);
		LecturerImpl lecturerModify=lecturerDAO.getLecturer(firstName, lastName);
		model.addAttribute("message", "Lecturer with name "+ firstName + " " + lastName +" has been modified");
		model.addAttribute("firstName", lecturerModify.getFirstName());
		model.addAttribute("lastName", lecturerModify.getLastName());
		model.addAttribute("idManagedProgram", lecturerModify.getIdManagedProgram());
		return "displayLecturer";
	}
	
	@RequestMapping(value="/modify/id/{id}/phoneNumber/{phoneNumber}", method = RequestMethod.GET)
	public String modifyLecturerPhoneNumber(@PathVariable int id, @PathVariable String phoneNumber, ModelMap model) {
		lecturerDAO.updateLecturerPhoneNumber(id, phoneNumber);
		LecturerImpl lecturerModify=lecturerDAO.getLecturer(id);
		model.addAttribute("message", "Lecturer with id "+ id +" has been modified");
		model.addAttribute("firstName", lecturerModify.getFirstName());
		model.addAttribute("lastName", lecturerModify.getLastName());
		model.addAttribute("phoneNumber", lecturerModify.getPhoneNumber());
		return "displayLecturer";
	}
	
	@RequestMapping(value="/modify/firstName/{firstName}/lastName/{lastName}/phoneNumber/{phoneNumber}", method = RequestMethod.GET)
	public String modifyLecturerPhoneNumber(@PathVariable String firstName, @PathVariable String lastName,
			@PathVariable String phoneNumber, ModelMap model) {
		lecturerDAO.updateLecturerPhoneNumber(firstName, lastName, phoneNumber);
		LecturerImpl lecturerModify=lecturerDAO.getLecturer(firstName, lastName);
		model.addAttribute("message", "Lecturer with name "+ firstName + " " + lastName +" has been modified");
		model.addAttribute("firstName", lecturerModify.getFirstName());
		model.addAttribute("lastName", lecturerModify.getLastName());
		model.addAttribute("phoneNumber", lecturerModify.getPhoneNumber());
		return "displayLecturer";
	}
	
	@RequestMapping(value="/modify/id/{id}/roomNumber/{roomNumber}", method = RequestMethod.GET)
	public String modifyLecturerRoomNumber(@PathVariable int id, @PathVariable String roomNumber, ModelMap model) {
		lecturerDAO.updateLecturerRoomNumber(id, roomNumber);
		LecturerImpl lecturerModify=lecturerDAO.getLecturer(id);
		model.addAttribute("message", "Lecturer with id "+ id +" has been modified");
		model.addAttribute("firstName", lecturerModify.getFirstName());
		model.addAttribute("lastName", lecturerModify.getLastName());
		model.addAttribute("roomNumber", lecturerModify.getRoomNumber());
		return "displayLecturer";
	}
	
	@RequestMapping(value="/modify/firstName/{firstName}/lastName/{lastName}/roomNumber/{roomNumber}", method = RequestMethod.GET)
	public String modifyLecturerRoomNumber(@PathVariable String firstName, @PathVariable String lastName,
			@PathVariable String roomNumber, ModelMap model) {
		lecturerDAO.updateLecturerRoomNumber(firstName, lastName, roomNumber);
		LecturerImpl lecturerModify=lecturerDAO.getLecturer(firstName, lastName);
		model.addAttribute("message", "Lecturer with name "+ firstName + " " + lastName +" has been modified");
		model.addAttribute("firstName", lecturerModify.getFirstName());
		model.addAttribute("lastName", lecturerModify.getLastName());
		model.addAttribute("phoneNumber", lecturerModify.getRoomNumber());
		return "displayLecturer";
	}
	
	@RequestMapping(value ="/delete/id/{id}", method = RequestMethod.GET) 
	public String deleteSongwriterbyId(@PathVariable int id, ModelMap model) { 
		LecturerImpl lecturerDelete=lecturerDAO.getLecturer(id);
		lecturerDAO.deleteLecturer(id);
		model.addAttribute("message", "Lecturer with id "+ id +" and details below have been deleted from the system");
		model.addAttribute("firstName", lecturerDelete.getFirstName());
		model.addAttribute("lastName", lecturerDelete.getLastName());
		return "displayLecturer";
	}
}