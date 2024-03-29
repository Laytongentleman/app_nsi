package org.layton.SpringBootThymeleaf.controller;

import jdk.nashorn.internal.objects.Global;
import org.layton.SpringBootThymeleaf.RestClientService;
import org.layton.SpringBootThymeleaf.form.Enumtag;
import org.layton.SpringBootThymeleaf.form.TagList;
import org.layton.SpringBootThymeleaf.model.Game;
import org.layton.SpringBootThymeleaf.form.SearchForm;
import org.layton.SpringBootThymeleaf.model.*;
import org.layton.SpringBootThymeleaf.form.NoteForm;
import org.layton.SpringBootThymeleaf.model.Search;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Controller
public class MainController {
    /*private static HashMap<String, Integer> games = new HashMap<String, Integer>();*/
    private  static Game[] games;
    private String currentSearch = "";
    private  ArrayList<Game> selectedGames = new ArrayList<Game>();
    private Game current_game;
    ArrayList<Enumtag>  allTags = new ArrayList<Enumtag>(Arrays.asList(Enumtag.values()));
    ArrayList<Enumtag> selectedTags = new ArrayList<Enumtag>(Arrays.asList());


    @Autowired
    RestClientService restClientService;

    @PostConstruct
    public void initGames() {
        games = restClientService.getAllGames();
        for (Game g: games) {
            System.out.println(g.getName());

        };
    }



    static {

        /*
        persons.add(new Person("Louis", "Nguyen"));
        persons.add(new Person("Leon", "Gard"));
        games.add(new Game("HK", "Hollow Knight", 10, "Une aventure au fin fond des ténèbres d'Hallownest..", new ArrayList<String>(Arrays.asList("Aventure", "Plateformer2D", "Metroidvania"))));
        games.add(new Game("Minecraft", "Minecraft",7, "L'incroyable monde cubique aux possibilités plus qu'infinies !", new ArrayList<String>(Arrays.asList("Aventure", "Plateformer3D", "Openworld"))));
        games.add(new Game("zeldabotw", "Zelda Breath Of The Wild",9, "Une épopée légendaire !", new ArrayList<String>(Arrays.asList("Aventure", "Action","Openworld"))));
        games.add(new Game("zeldaoot", "Zelda Ocarina Of Time",8, "Une épopée légendaire !", new ArrayList<String>(Arrays.asList("Aventure", "Action","Openworld"))));
        games.add(new Game("PL", "Professeur Layton et l'Étrange village",10, "Every puzzle has an answer !", new ArrayList<String>(Arrays.asList("Aventure", "Puzzle", "pointandclick"))));
        games.add(new Game("PL2", "Professeur Layton et la boîte de Pandore", 10, "Every puzzle has an answer !", new ArrayList<String>(Arrays.asList("Aventure", "Puzzle", "pointandclick"))));
        games.add(new Game("PL3", "Professeur Layton et le Destin Perdu", 10, "Every puzzle has an answer !", new ArrayList<String>(Arrays.asList("Aventure", "Puzzle", "pointandclick"))));
        games.add(new Game("PL4", "Professeur Layton et l'Appel du Spectre", 8, "Every puzzle has an answer !", new ArrayList<String>(Arrays.asList("Aventure", "Puzzle", "pointandclick"))));
        games.add(new Game("Undertale", "Undertale", 9, "   In this world...it’s kill or be killed..", new ArrayList<String>(Arrays.asList("Aventure", "RPG", "decisions"))));
        games.add(new Game("LuigiM2", "Luigi's Mansion 2",7, "Depuis que Luigi a retrouvé son bon vieil aspirateur, la chasse aux fantomes est déclarée!", new ArrayList<String>(Arrays.asList("Aventure", "Plateformer3D", "Openworld"))));
        games.add(new Game("Teeworld", "Teeworld", 10, "Des ronds, un parcours, des guns et du fun", new ArrayList<String>(Arrays.asList("Multijoueur", "Plateformer2D", "Action"))));
        */

    }
    // application.properties

    @Value("${welcome.message}")
    private String message;

    private String errorMessage = "Vous n'avez pas respecté les champs";


    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
    public String index(Model model){
        model.addAttribute("message", message);
        return "index";
    }







    @RequestMapping(value = {"/biblio"}, method = RequestMethod.GET)
    public String biblio(Model model){

        model.addAttribute("allTags", allTags);
        model.addAttribute("games", games);

        SearchForm searchForm = new SearchForm();
        model.addAttribute("searchForm", searchForm);
        model.addAttribute("selectedTags",selectedTags);

        model.addAttribute("selectableEnumTag", Enumtag.values());
        TagList tagList = new TagList();
        tagList.setEnumTags(new ArrayList<Enumtag>(selectedTags));
        model.addAttribute("tagList",tagList);
        selectedGames.clear();
        System.out.println(selectedTags + " selected tags");
        System.out.println(selectedTags.isEmpty());

        if (currentSearch.equals("") && selectedTags.isEmpty()){

            for (Game game: games
                 ) {
                selectedGames.add(game);
            }

        }else if (currentSearch.equals("") != true) {
            for (Game game : games) {
                if (game.getName().equalsIgnoreCase(currentSearch)) {
                    selectedGames.add(game);
                } else {
                }
            }

            for (Game game : games) {
                if (game.getTags() != null) {
                    for (String tag : game.getTags().split(",")) {
                        if (tag.equalsIgnoreCase(currentSearch)) {
                            selectedGames.add(game);
                        }
                    }
                }

            }

            if (selectedGames.size() < 1) {

                for (Game game : games) {
                    for (int i = 0; i < game.getName().toCharArray().length; i++) {
                        int max_occ = 0;
                        int occ = 0;
                        if (Character.toLowerCase(game.getName().toCharArray()[i]) == currentSearch.charAt(0)) {
                            System.out.println("OwO");
                            for (int j = 0; j < currentSearch.toCharArray().length; j++) {
                                System.out.println("OwO");
                                if (i + j < game.getName().toCharArray().length && j < currentSearch.toCharArray().length) {
                                    if (Character.toLowerCase(game.getName().toCharArray()[i + j]) == Character.toLowerCase(currentSearch.toCharArray()[j])) {
                                        occ++;
                                    } else {
                                        occ = 0;
                                    }
                                    if (occ > max_occ) {
                                        max_occ = occ;
                                    }


                                } else {
                                    break;

                                }
                            }
                        }
                        if (max_occ > 2) {
                            selectedGames.add(game);
                        }
                    }
                    if (game.getTags() instanceof String) {
                        for (String tag : game.getTags().split(",")) {
                            for (int i = 0; i < tag.toCharArray().length; i++) {
                                int max_occ = 0;
                                int occ = 0;
                                if (Character.toLowerCase(tag.toCharArray()[i]) == Character.toLowerCase(currentSearch.toCharArray()[0])) {
                                    for (int j = 0; j < currentSearch.toCharArray().length; j++) {
                                        if (i + j < tag.toCharArray().length && j < currentSearch.toCharArray().length) {
                                            if (Character.toLowerCase(tag.toCharArray()[i + j]) == Character.toLowerCase(currentSearch.toCharArray()[j])) {
                                                occ++;
                                            } else {
                                                occ = 0;
                                            }
                                            if (occ > max_occ) {
                                                max_occ = occ;
                                            }

                                        } else {
                                            break;
                                        }
                                    }
                                }
                                if (max_occ > 2) {
                                    selectedGames.add(game);
                                }
                            }
                        }
                    }
                }
            }
        }else {
            if (selectedTags.isEmpty() != true) {
                for (Game game : games
                ) {
                    for (String tag : game.getTags().split(",")
                    ) {
                        if (selectedTags != null) {
                            if (selectedTags.contains(Enumtag.valueOf(tag)) && selectedGames.contains(game) != true) {
                                selectedGames.add(game);
                                break;
                            }
                        }


                    }
                }
            }
        }

        model.addAttribute("selectedGames", selectedGames);
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
    public String showGame(@RequestParam("id") String id, Model model){
        for (Game game: games
             ) {
            if (game.getId().equalsIgnoreCase(id)){
                current_game = game;

            }

        }
        float com_note = current_game.getSumVote()/ current_game.getNbVote();
        com_note = (float)Math.round(com_note * 10)/10;
        model.addAttribute("com_note", com_note);
        model.addAttribute("game", current_game);
        NoteForm noteForm = new NoteForm();
        model.addAttribute("noteForm", noteForm);
        noteForm.setName(current_game.getId());

        return "game_template";
    }

    @RequestMapping(value = {"/note"}, method = RequestMethod.POST)
    public ModelAndView note(Note noteForm, BindingResult result, Model model){
        double note = noteForm.getNote();

        if (note >= 0 && note <= 10){
            current_game.setSumVote((float)current_game.getSumVote()+(float)note);
            current_game.setNbVote(current_game.getNbVote()+1);
            //model.addAttribute("games", games);
            //model.addAttribute("currentSearch", currentSearch);
            //return "redirect:/biblio";
            restClientService.updateGame(current_game.getId(),  current_game);
        }else{
            model.addAttribute("errorMessage", errorMessage);
        }




        return new ModelAndView("redirect:/game_template?id=" +current_game.getId(),"model", model);

    }



    @RequestMapping(value = {"/update"}, method = RequestMethod.GET)
    public String showForm(@RequestParam("id") String id, Model model){
        initGames();
        for (Game game: games
        ) {
            if (game.getId().equalsIgnoreCase(id)){
                current_game = game;

            }

        }
        model.addAttribute("game", current_game);
        Game updatedGame = new Game();
        model.addAttribute("updatedGame", updatedGame);



        return  "update_form";
    }



    @RequestMapping(value = {"/update_process"}, method = RequestMethod.POST)
    public ModelAndView update(Game updatedGame, BindingResult result, Model model){

        /*current_game = new_game;
        restClientService.updateGame(current_game.getId(), new_game);*/

        restClientService.updateGame(current_game.getId(),updatedGame);

        return new ModelAndView("redirect:/update?id=" +current_game.getId(),"model", model);


    }

    @RequestMapping(value = {"/upd"}, method = RequestMethod.POST)
    public ModelAndView upd( TagList tagList, BindingResult result, Model model){
        System.out.println(tagList + "obj");
        System.out.println(tagList.getEnumTags() + " test");
        /*current_game = new_game;
        restClientService.updateGame(current_game.getId(), new_game);*/
        selectedTags = tagList.getEnumTags();
        return new ModelAndView("redirect:/biblio","model", model);

        //return new ModelAndView("redirect:/biblio","model", model);


    }

    /*@RequestMapping(value = {"/histoire"}, method = RequestMethod.POST)
    public ModelAndView search(Search searchForm, BindingResult result, Model model){
        currentSearch = searchForm.getValue();
        //model.addAttribute("games", games);
        //model.addAttribute("currentSearch", currentSearch);
        //return "redirect:/biblio";
        return new ModelAndView("redirect:/biblio","model", model);

    }*/
}
