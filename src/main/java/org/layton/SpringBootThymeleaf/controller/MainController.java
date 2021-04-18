package org.layton.SpringBootThymeleaf.controller;


import org.layton.SpringBootThymeleaf.form.PersonForm;
import org.layton.SpringBootThymeleaf.form.SearchForm;
import org.layton.SpringBootThymeleaf.model.*;
import org.layton.SpringBootThymeleaf.form.VoteForm;
import org.layton.SpringBootThymeleaf.form.NoteForm;
import org.layton.SpringBootThymeleaf.model.Search;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


@Controller
public class MainController {
    /*private static HashMap<String, Integer> games = new HashMap<String, Integer>();*/
    private static ArrayList<Game> games = new ArrayList<Game>();
    private static List<Person> persons = new ArrayList<Person>();
    private static List<Vote> votes = new ArrayList<Vote>();
    private String currentSearch = "";
    private static ArrayList<Game> selectedGames = new ArrayList<Game>();

    static {
        persons.add(new Person("Louis", "Nguyen"));
        persons.add(new Person("Leon", "Gard"));
        games.add(new Game("HK", 10, "Une aventure au fin fond des ténèbres d'Hallownest..", new ArrayList<String>(Arrays.asList("Aventure", "Plateformer2D", "Metroidvania"))));
        games.add(new Game("Minecraft", 10, "L'incroyable plateforme plus polyvalente qu'un couteau suisse!", new ArrayList<String>(Arrays.asList("Aventure", "Plateformer3D", "Openworld"))));

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
        model.addAttribute("selectedGames", selectedGames);
        SearchForm searchForm = new SearchForm();
        model.addAttribute("searchForm", searchForm);
        selectedGames.clear();
        if (currentSearch.equals("")){

            for (Game game: games
                 ) {
                selectedGames.add(game);

            }

        }else{
            for (Game game:  games) {
                if (game.getName().equalsIgnoreCase(currentSearch)) {
                    selectedGames.add(game);
                } else {
                    System.out.println(game.getName() + " " + "search is:" + currentSearch);
                }

            }

        }

        currentSearch = "";

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
        //games.put(noteForm.getName(), 2);
        System.out.println(" le nom est :" + noteForm.getName()+ " "+ String.valueOf(noteForm.getNote()));
        return "addNote";

    }


    @RequestMapping(value = {"/search"}, method = RequestMethod.POST)
    public ModelAndView search(Search searchForm, BindingResult result, Model model){
        currentSearch = searchForm.getValue();
        //model.addAttribute("games", games);
        //model.addAttribute("currentSearch", currentSearch);
        //return "redirect:/biblio";
        return new ModelAndView("redirect:/biblio","model", model);

    }

    @RequestMapping(value = {"/game_template"}, method = RequestMethod.GET)
    public String showGame(@RequestParam("name") String name, Model model){
        model.addAttribute("name", name);
        return "game_template";
    }

}
