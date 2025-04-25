package com.example.backendcinema.Payment.Controller;

import com.example.backendcinema.Payment.Service.ZalopayService;
import com.example.backendcinema.Payment.modal.OrderIntermediate;
import com.example.backendcinema.Payment.modal.ZalopayTransaction;
import com.example.backendcinema.Payment.request.ZalopayRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/zalopay")
@CrossOrigin("*")
public class ZalopayController {

    @Autowired
    private ZalopayService zalopayService;

    @PostMapping
    public ResponseEntity<String> createZalopayOrder(@RequestBody ZalopayRequest zaloPayRequest) {
        String response = zalopayService.createOrder(zaloPayRequest);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/order-status/{appTransId}")
    public ResponseEntity<String> getOrderStatus(@PathVariable String appTransId) {
        String response = zalopayService.getOrderStatus(appTransId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/callback")
    public String zalopayCallback(@RequestParam Map<String, String> queryParams) {
        return zalopayService.processCallback(queryParams);
    }

    @GetMapping("/transactions/{accountId}")
    public ResponseEntity<List<ZalopayTransaction>> getTransactionsByAccountId(@PathVariable String accountId) {
        List<ZalopayTransaction> transactions = zalopayService.getTransactionsByAccountId(accountId);
        if (transactions.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('Admin') or hasAuthority('User')")
    @GetMapping("/fillter/{appTransId}")
    public ResponseEntity<List<ZalopayTransaction>> getTransactionsByAppTransId(@PathVariable String appTransId) {
        List<ZalopayTransaction> transactions = zalopayService.getTransactionsByAppTransId(appTransId);
        if (transactions != null && !transactions.isEmpty()) {
            return new ResponseEntity<>(transactions, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PreAuthorize("hasAnyAuthority('Admin') or hasAuthority('Manager')")
    @GetMapping("/getAllTranstion")
    public List<ZalopayTransaction> getAllTranstion (){
        return zalopayService.getAllTranstion();
    }

}
