package org.springframework.samples.petclinic.recoveryroom;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/recoveryroom")
public class RecoveryRoomController {
    private static final String VIEW_RECOVERY_ROOMS_CREATE_FORM = "recoveryroom/createOrUpdateRecoveryRoomForm";
    private static final String WELCOME = "welcome";
    private RecoveryRoomService serv;
    
    @Autowired
    public RecoveryRoomController(RecoveryRoomService service) {
    	this.serv = service;
    }

    @GetMapping("/create") 	
    public String initRecoveryRoom(ModelMap map) {
    	map.addAttribute("recoveryRoom", new RecoveryRoom());
    	map.addAttribute("types", serv.getAllRecoveryRoomTypes());
    	return VIEW_RECOVERY_ROOMS_CREATE_FORM;
    }
    
    @PostMapping(path = "/create")
    public String createRecoveryRoom(@Valid RecoveryRoom recoveryRoom, BindingResult br, ModelMap map){
        if(!br.hasErrors()){
        	serv.save(recoveryRoom);
            map.addAttribute("message", "Product succesfully save");
            return WELCOME;
        } else {
            map.addAttribute("product", recoveryRoom);
            map.addAttribute("types", serv.getAllRecoveryRoomTypes());
        }
        return VIEW_RECOVERY_ROOMS_CREATE_FORM;
    }
}
