package com.relocation.relocation.Controller;

import com.relocation.relocation.Model.Relocation;
import com.relocation.relocation.Repository.relocationRepository;
import com.relocation.relocation.Service.RelocationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.Base64;

@RestController
@Controller
public class RelocationController {
    @Autowired
    RelocationServiceImpl relocationService;
    @Autowired
    relocationRepository relocationRepository;
    @Autowired
    private MessageSource messageSource;

    //Calling Home Page
    @RequestMapping(value = "/CanalBox.rw", method = RequestMethod.GET)
    public String showHomePage(){
        System.out.println("Home Page!!!");
        return "homeClient";
    }
    
    
    
    @GetMapping("/hello")
    public String hello() {
        return "Hello, Spring Boot!";
    }


    //Checking Status using Relocation class
    @RequestMapping(value = "/StatusRelocation", method = RequestMethod.GET)
    public String showStatus(@ModelAttribute("relocation") Relocation relocation, Model model){
        System.out.println("Relocation Status Page!!!");
        Relocation theRelocation = relocationService.findRelocationBySerialNumber(relocation);
        System.out.println("Request Status: "+theRelocation.getNewAddress()+" "+theRelocation.getNewAddress()+" "+theRelocation.getTelNo()+" "+theRelocation.getEligibility()+" "+theRelocation.getTxid()+" "+theRelocation.getSchedule()+" to be displayed!!!");
        model.addAttribute("relocation", theRelocation);
        return "statusClient";
    }

    //Calling Status Form using SerialNumber
    @RequestMapping(value = "/CheckStatus", method = RequestMethod.GET)
    public String showStatusCheck(Model model){
        Relocation relocation = new Relocation();
        model.addAttribute("relocation", relocation);
        return "statusClientForm";
    }

    //Status form
    @RequestMapping(value = "/statusForm", method = RequestMethod.POST)
    public String statusForm(@RequestParam("serialNumber") String serialNumber,
                             Model model){
        try {
            System.out.println("Relocation Check Status Page!!!");
            Relocation relocation = relocationRepository.findById(serialNumber).get();
            System.out.println("Request Status: " + relocation.getNewAddress() + " " + relocation.getNewAddress() + " " + relocation.getTelNo() + " " + relocation.getEligibility() + " " + relocation.getTxid() + " " + relocation.getSchedule() + " to be displayed!!!");
            model.addAttribute("relocation", relocation);
            return "statusClient";
        }catch (Exception e){
            System.out.println("Status Error : "+e.getMessage());
            return "relocationForm";
        }
    }

    //Calling the Relocation Form Page
    @RequestMapping(value = "/RelocationForm", method = RequestMethod.GET)
    public String showRelocationForm(Model model){
        Relocation relocation = new Relocation();
        model.addAttribute("relocation", relocation);
        System.out.println("Relocation Form Page!!!");
        return "relocationForm";
    }

    //Registering new request
    @RequestMapping(value = "/addRequest", method = RequestMethod.POST)
    public String addRequest(@RequestParam("serialNumber") String serialNumber,
                             @RequestParam("address") String newAddress,
                             @RequestParam("telephone") String telNo,
                             @RequestParam("picture") MultipartFile file,
                             Model model){
        System.out.println("Data from the form "+serialNumber+" "+newAddress+" "+telNo+" "+file.getOriginalFilename());
        try{
            String eligibility = "Pending";
            String txid = "Bill Pending";
            String schedule = "Schedule Pending";
            Relocation relocation = new Relocation();
            relocation.setSerialNumber(serialNumber);
            relocation.setNewAddress(newAddress);
            relocation.setTelNo(telNo);
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            if (fileName.contains("..")) {
                System.out.println("Not a valid file, must be in jpg format");
                model.addAttribute("message", "Not a valid file, must be in jpg format");
            }
            try {
                relocation.setPicture(Base64.getEncoder().encodeToString(file.getBytes()));
            }catch (Exception e){
                System.out.println("Image Error "+e.getMessage());
            }
            relocation.setEligibility(eligibility);
            relocation.setTxid(txid);
            relocation.setSchedule(schedule);
            System.out.println(relocation.getSerialNumber()+" "+relocation.getNewAddress()+" "+relocation.getTelNo()+" "+relocation.getEligibility()+" "+relocation.getTxid()+" "+relocation.getSchedule()+" to be Saved!!!");
            if (!relocationRepository.findById(serialNumber).isEmpty()){
                System.out.println("The data exist!!!!");
                model.addAttribute("message", "The request already exist, Please Check the status!!!");
                return "/RelocationForm";
            }else {
                Relocation registerRequest = relocationService.registerRelocation(relocation);
                System.out.println(relocation.getSerialNumber()+" "+relocation.getNewAddress()+" "+relocation.getTelNo()+" "+relocation.getEligibility()+" "+relocation.getTxid()+" "+relocation.getSchedule()+" SuccessFully Saved!!!");
                model.addAttribute("message", "The request successfully submitted, You can check status!!!");
                model.addAttribute("relocation", relocation);
                model.addAttribute("serialNumber", serialNumber);
                return "statusClient";
            }
        } catch (Exception e){
            System.out.println("Error : "+e.getMessage());
        }
        System.out.println("Data didn't saved!!!");
        return "redirect:/RelocationForm";
    }

    //Calling update page
    @GetMapping("/EditRelocation")
    public String updateRelocation(@ModelAttribute("serialnumber") String serialNumber, Model model){
        Relocation relocation =relocationRepository.findById(serialNumber).get();
        System.out.println(relocation.getSerialNumber()+" "+relocation.getNewAddress()+" "+relocation.getTelNo()+" "+relocation.getEligibility()+" "+relocation.getTxid()+" "+relocation.getSchedule()+" from database!!!");
        model.addAttribute("relocation", relocation);
        return "updateClientForm";
    }

    //Submitting the updates
    @PostMapping("/Update")
    public String updatingRelocation(@RequestParam("serialNumber") String serialNumber,
                                     @RequestParam("address") String newAddress,
                                     @RequestParam("telephone") String telNo,
                                     @RequestParam("picture") MultipartFile file,
                                     Model model){
        System.out.println("Data from the form "+serialNumber+" "+newAddress+" "+telNo+" "+file.getOriginalFilename());
        try{
            String eligibility = "Pending";
            String txid = "Bill Pending";
            String schedule = "Schedule Pending";
            Relocation relocation = new Relocation();
            relocation.setSerialNumber(serialNumber);
            relocation.setNewAddress(newAddress);
            relocation.setTelNo(telNo);
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            if (fileName.contains("..")) {
                System.out.println("Not a valid file, must be in jpg format");
                model.addAttribute("message", "Not a valid file, must be in jpg format");
            }
            try {
                relocation.setPicture(Base64.getEncoder().encodeToString(file.getBytes()));
            }catch (Exception e){
                System.out.println("Image Error "+e.getMessage());
            }
            relocation.setEligibility(eligibility);
            relocation.setTxid(txid);
            relocation.setSchedule(schedule);
            System.out.println(relocation.getSerialNumber()+" "+relocation.getNewAddress()+" "+relocation.getTelNo()+" "+relocation.getPicture()+" "+relocation.getEligibility()+" "+relocation.getTxid()+" "+relocation.getSchedule()+" to be update!!!");
            Relocation updateRequest = relocationService.updateRelocation(relocation);
            if (updateRequest != null){
                System.out.println(relocation.getSerialNumber()+" "+relocation.getNewAddress()+" "+relocation.getTelNo()+" "+relocation.getPicture()+" "+relocation.getEligibility()+" "+relocation.getTxid()+" "+relocation.getSchedule()+" successfuly updated!!!");
                model.addAttribute("message", "The request successfully submitted, You can check status!!!");
                model.addAttribute("relocation", relocation);
                model.addAttribute("serialNumber", serialNumber);
                return "/EditRelocation";
            }
        } catch (Exception e){
            System.out.println("Error : "+e.getMessage());
        }
        System.out.println("Data didn't updated!!!");
        return "redirect:/EditRelocation";
    }
}
