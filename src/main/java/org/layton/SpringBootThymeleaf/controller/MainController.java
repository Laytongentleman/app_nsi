package org.layton.SpringBootThymeleaf.controller;


import org.layton.SpringBootThymeleaf.form.PersonForm;
import org.layton.SpringBootThymeleaf.model.Person;
import org.layton.SpringBootThymeleaf.form.VoteForm;
import org.layton.SpringBootThymeleaf.model.Vote;
import org.layton.SpringBootThymeleaf.form.NoteForm;
import org.layton.SpringBootThymeleaf.model.Note;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


@Controller
public class MainController {
    private static HashMap<String, Integer> games = new HashMap<String, Integer>();
    private static List<Person> persons = new ArrayList<Person>();
    private static List<Vote> votes = new ArrayList<Vote>();

    static {
        persons.add(new Person("Louis", "Nguyen"));
        persons.add(new Person("Leon", "Gard"));
        games.put("HK", 10);
        games.put("minecraft", 8);

    }
    // application.properties

    @Value("${welcome.message}")
    private String message;

    @Value("${error.message}")
    private String errorMessage;


    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
    public String index(Model model){
        model.addAttribute("message", message);
        return "index";
    }

    @RequestMapping(value = {"/personList"}, method = RequestMethod.GET)
    public String personList(Model model){
        model.addAttribute("persons", persons);

        return "personList";
    }


    @RequestMapping(value = {"/addPerson"}, method = RequestMethod.GET)
    public String showAddPersonPage(Model model) {
        PersonForm personForm = new PersonForm();
        model.addAttribute("personForm", personForm);

        return "addPerson";
    }

    @RequestMapping(value = { "/addPerson" }, method = RequestMethod.POST)
    public String savePerson(Model model, //
                             @ModelAttribute("personForm") PersonForm personForm) {

        String firstName = personForm.getFirstName();
        String lastName = personForm.getLastName();

        if (firstName != null && firstName.length() > 0 //
                && lastName != null && lastName.length() > 0) {
            Person newPerson = new Person(firstName, lastName);
            persons.add(newPerson);

            return "redirect:/personList";
        }
        model.addAttribute("errorMessage", errorMessage);
        return "addPerson";
    }


    @RequestMapping(value = {"/addVote"}, method = RequestMethod.GET)
    public String showAddvote(Model model) {

        VoteForm voteForm = new VoteForm();
        model.addAttribute("VoteForm", voteForm);

        return "addVote";
    }

    @RequestMapping(value = {"/addVote"}, method = RequestMethod.POST)
    public String saveVote(Model model, @ModelAttribute("voteForm") VoteForm voteForm ){
        int voteValue = voteForm.getVoteValue();
        Vote newVote = new Vote(voteValue);

        votes.add(newVote);

        return "redirect:/voteList";

    }

    @RequestMapping(value = {"/voteList"}, method = RequestMethod.GET)
    public String voteList(Model model){
        model.addAttribute("votes", votes);

        return "voteList";
    }

    @RequestMapping(value = {"/biblio"}, method = RequestMethod.GET)
    public String biblio(Model model){
        model.addAttribute("games", games);
        return "biblio";
    }
    /*
    @RequestMapping(value = {"/like"}, method = RequestMethod.POST)
    public String like(Model model, @ModelAttribute("Note") NoteForm noteform) {
        model.addAttribute("games", games);
        games.put(noteform.getName(), noteform.getNote());

        return "redirect:/biblio";
    }
    @RequestMapping(value = {"/like"}, method = RequestMethod.GET)
    public String like(Model model){
        return "like";
    }*/



    @RequestMapping(value = {"/addNote"}, method = RequestMethod.GET)
    public String showAddNote(Model model) {

        NoteForm noteForm = new NoteForm();
        model.addAttribute("noteForm", noteForm);
        noteForm.setName("HK");
        return "addNote";
    }


    @RequestMapping(value = {"/addNote"}, method = RequestMethod.POST)
    public String saveNote(NoteForm noteForm, BindingResult result, Model model){
    //public String saveNote(Model model, @ModelAttribute("noteForm") NoteForm noteForm ){
        model.addAttribute("games", games);
        games.put(noteForm.getName(), 2);
        System.out.println(" le nom est :" + noteForm.getName());
        return "addNote";

    }


}
