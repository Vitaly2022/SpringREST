package org.example.controllers;

//import io.swagger.v3.oas.annotations.Operation;
//import io.swagger.v3.oas.annotations.media.Content;
//import io.swagger.v3.oas.annotations.media.Schema;
//import io.swagger.v3.oas.annotations.responses.ApiResponse;
//import io.swagger.v3.oas.annotations.responses.ApiResponses;
//import io.swagger.v3.oas.annotations.tags.Tag;

import org.example.entities.Card;
import org.example.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

//@Tag(name = "Student Service", description = "Service to find Students")
@RestController


public class CardsService {

    @Autowired
    private CardRepository cardRepository;

    //optional класс Обязательно почитать
    @GetMapping(value = "/card/{id}")
    public ResponseEntity<Card> findCardbyId(@PathVariable int id) {
        Card card = cardRepository.findById(id).get();
        //метод findDyId() - класс Optional (с Java 8 был введен) потому и нужен get() после него

        return new ResponseEntity<>(card, HttpStatus.OK);
    }

    @GetMapping(value = "/cardowner/{name}")  //ищем карту по имени владельца
    public ResponseEntity<Card> cardOwner(@PathVariable String name) {
        Card card = cardRepository.findByName(name);

        return new ResponseEntity<>(card, HttpStatus.FOUND);
    }

    @GetMapping(value = "/cardnumber/{cardnumber}")  //ищем карту по номеру
    public ResponseEntity<Card> cardNumber(@PathVariable String cardnumber) {
        Card card = cardRepository.findByCardnumber(cardnumber);

        return new ResponseEntity<>(card, HttpStatus.FOUND);
    }

    @Transactional
    @PostMapping("/transfer") // делаем перевод
    // - через JSON класс Трансфер 1е значение с какой карты, 2е - сумма, 3е - номер карты куда переводим
    //пример в POSTMAN
    // {"cardSender":"123456","sum":100,"cardRecipient":"456789"}
    //метод POST
    public ResponseEntity<Card> transferSum(@RequestBody Transfer transfer) {
        Card card1 = cardRepository.findByCardnumber(transfer.getCardSender());
        int b = card1.getBalance() - transfer.sum; //минусуем кто переводит
        card1.setBalance(b);

        Card card2 = cardRepository.findByCardnumber(transfer.getCardRecipient());
        int b2 = card2.getBalance() + transfer.sum;
        card2.setBalance(b2);
        cardRepository.save(card1);
        cardRepository.save(card2);

        return new ResponseEntity<>(HttpStatus.OK);

    }

    @Transactional
    @PostMapping("/card") //создаем нового владельца карты, также через JSON
    public ResponseEntity<Card> createCard(@RequestBody Card card) {
        cardRepository.save(card);

        return new ResponseEntity<>(card, HttpStatus.CREATED);
    }

//    @Operation(summary = "Get student by the ID.")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "Find the student",
//                    content = { @Content(mediaType = "application/json",
//                            schema = @Schema(implementation = Student.class)) }),
//            @ApiResponse(responseCode = "400", description = "Invalid ID supplied",
//                    content = @Content),
//            @ApiResponse(responseCode = "404", description = "Student not found",
//                    content = @Content) })

}
