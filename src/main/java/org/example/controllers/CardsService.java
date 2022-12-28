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
import org.springframework.web.bind.annotation.*;

//@Tag(name = "Student Service", description = "Service to find Students")
@RestController


public class CardsService {

    @Autowired
    private CardRepository cardRepository ;


    @GetMapping(value = "/card/{id}")
    public ResponseEntity<Card> findCard(@PathVariable int id)
    {
        Card card = cardRepository.findById(id).get();
        return new ResponseEntity<>(card, HttpStatus.OK);
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

    @PostMapping("/transfer")
    public ResponseEntity<Card> createCard(@RequestBody Transfer transfer)
    {
        Card card1 = cardRepository.findByCardnumber(transfer.getCardSender()).get();
        int b = card1.getBalance()-transfer.sum;
        card1.setBalance(b);
        cardRepository.save(card1);

        Card card2 = cardRepository.findByCardnumber(transfer.getCardRecipient()).get();
        int b2 = card2.getBalance()+transfer.sum;
        card2.setBalance(b2);
        cardRepository.save(card2);

        return new ResponseEntity<>("Transfer OK" , HttpStatus.OK);

    }

    @PostMapping("/card")
    public ResponseEntity<Card> createCard(@RequestBody Card card)
    {
        cardRepository.save(card);

        return new ResponseEntity<>(card, HttpStatus.CREATED);
    }
//
//    @GetMapping(value = "/grooup/{id}")
//    public ResponseEntity<Grooup> findGrooup(@PathVariable int id)  //это говорит о id  тут и там id одна и таже
//    {
//        Grooup grooup = grooupRepository.findById(id).get();
//        return new ResponseEntity<>(grooup, HttpStatus.OK);
//    }
}
