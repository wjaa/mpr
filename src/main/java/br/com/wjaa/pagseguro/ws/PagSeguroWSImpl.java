/**
 * Copyright [2011] [PagSeguro Internet Ltda.]

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
 */
package br.com.wjaa.pagseguro.ws;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import org.springframework.stereotype.Service;

import br.com.uol.pagseguro.domain.AccountCredentials;
import br.com.uol.pagseguro.domain.Currency;
import br.com.uol.pagseguro.domain.PaymentRequest;
import br.com.uol.pagseguro.domain.Transaction;
import br.com.uol.pagseguro.domain.TransactionSearchResult;
import br.com.uol.pagseguro.domain.TransactionSummary;
import br.com.uol.pagseguro.exception.PagSeguroServiceException;
import br.com.uol.pagseguro.service.NotificationService;
import br.com.uol.pagseguro.service.TransactionSearchService;
import br.com.wjaa.mpr.entity.Cupom;
import br.com.wjaa.mpr.entity.Pedido;
import br.com.wjaa.mpr.entity.PortaRetrato;

@Service
public class PagSeguroWSImpl implements PagSeguroWS{

    private static final String TOKEN_PS = "CC6161E3296C4099BA294973EE80B035";
	private static final String USER_PS = "wag182@gmail.com";

	/**
     * Class with a main method to illustrate the usage of the domain class PaymentRequest
     * @throws PagSeguroServiceException 
     */
    public URL criarPagamento(Pedido pedido, Cupom cupom) throws PagSeguroServiceException {

        // Instantiate a new payment request
        PaymentRequest paymentRequest = new PaymentRequest();

        // Sets the currency
        paymentRequest.setCurrency(Currency.BRL);

        PortaRetrato pr = pedido.getPortaRetrato();
        BigDecimal preco = createBigDecimal( (pedido.getValor() - this.getDesconto(pedido.getValor(), cupom)));
        // Add an item for this payment request
        paymentRequest.addItem(pedido.getId().toString(), 
        		pr.getNome(), new Integer(1), preco, new Long(1000),
                null);

        // Add another item for this payment request
        //paymentRequest.addItem("0002", "Notebook Rosa", new Integer(2), new BigDecimal("2560.00"), new Long(750), null);

        // Sets a reference code for this payment request, it's useful to
        // identify this payment in future notifications.
        paymentRequest.setReference(pr.getPrCode());

        // Sets shipping information for this payment request
        //paymentRequest.setShippingType(ShippingType.SEDEX);
       // paymentRequest.setShippingAddress("BRA", "SP", "Sao Paulo", "Jardim Paulistano", "01452002",
               // "Av. Brig. Faria Lima", "1384", "5o andar");

        // Sets your customer information.
        //paymentRequest.setSender("Joao Comprador", "comprador@uol.com.br", "11", "56273440", "CPF", "888.263.551-18");

        // Sets notificationURL information
        paymentRequest.setNotificationURL("http://www.meuportaretrato.com/notificacao");

        // Sets redirectURL
        paymentRequest.setRedirectURL("http://www.meuportaretrato.com/retornoPagamento");


        // Register this payment request in PagSeguro, to obtain the payment
        // URL for redirect your customer.
        URL paymentURL = paymentRequest.register(new AccountCredentials(USER_PS,
                TOKEN_PS));
        
        //redirecionar o comprador para URL de pagamento.
        //https://pagseguro.uol.com.br/v2/checkout/payment.html?code=8CF4BE7DCECEF0F004A6DFA0A8243412
        return paymentURL;

    }
    
    
    private Double getDesconto(Double preco , Cupom cupom) {
		if (cupom != null){
			return preco * cupom.getPorcentagem() / 100;
		}
		return 0.0;
	}


	public Transaction notificacao(String notificationCode) throws PagSeguroServiceException {

        // Substitute the code below with a notification code for your
        // transaction.
        // You receive this notification code through a post on the URL that you
        // specify in
        // this page:
        /**
         * @link https://pagseguro.uol.com.br/integracao/notificacao-de-transacoes .jhtml
         */
        //String notificationCode = "512AF8-49E2FBE2FB71-799426FFAC58-90F5C1";

            // Check transaction
        Transaction transaction = NotificationService.checkTransaction(new AccountCredentials(USER_PS,
                TOKEN_PS), notificationCode);

        if (transaction != null) {
            System.out.println("transaction code: " + transaction.getCode());
            System.out.println("transaction status: " + transaction.getStatus());
        }
        
        return transaction;
    }

    
    
    public Transaction buscaTransactionByCode(String transactionCode) throws PagSeguroServiceException {

        // Substitute the code below with a valid transaction code for your
        // account
        //String transactionCode = "41F4640C-4A84-435B-831C-8EAD876A3B93";

            // Substitute the parameters below with your credentials (e-mail and
            // token)
    	Transaction transaction = TransactionSearchService.searchByCode(new AccountCredentials(USER_PS,
                TOKEN_PS), transactionCode);

        if (transaction != null) {
            printTransaction(transaction);
        }
        
        return transaction;
    }
    
    
    public TransactionSearchResult buscarTransacaoByDate(Date initialCalendar, Date finalCalendar) 
    		throws SecurityException, NoSuchFieldException, IllegalArgumentException,
    IllegalAccessException, NoSuchMethodException, InvocationTargetException, PagSeguroServiceException {

	
	    // Substitute the parameters below with your credentials (e-mail and
	    // token)
	    AccountCredentials credentials = new AccountCredentials(USER_PS,
	            TOKEN_PS);
		
	    TransactionSearchResult result = TransactionSearchService.searchByDate(credentials, initialCalendar,
	            finalCalendar, new Integer(1), new Integer(10));
		
		if (result != null) {
		    System.out.println("Search date: " + result.getDate());
		    System.out.println(result.getResultsInThisPage() + " results in the page " + result.getPage() + " of "
		            + result.getTotalPages() + " pages.");
		
		    List listTransactionSummaries = result.getTransactionSummaries();
		    Iterator transactionSummariesIterator = listTransactionSummaries.iterator();
		    int counter = 0;
		    while (transactionSummariesIterator.hasNext()) {
		        TransactionSummary currentTransactionSummary = (TransactionSummary) transactionSummariesIterator.next();
		        System.out.println();
		        System.out.println("Transaction: " + ++counter);
		        System.out.println("Code: " + currentTransactionSummary.getCode());
		        System.out.println("Reference: " + currentTransactionSummary.getReference());
		        System.out.println("Date: " + currentTransactionSummary.getDate());
		        System.out.println("Disccount amount: " + currentTransactionSummary.getDiscountAmount());
		        System.out.println("Extra amount: " + currentTransactionSummary.getExtraAmount());
		        System.out.println("Fee amount: " + currentTransactionSummary.getFeeAmount());
		        System.out.println("Transaction amount: " + currentTransactionSummary.getGrossAmount());
		        System.out.println("Last event date: " + currentTransactionSummary.getLastEvent());
		        System.out.println("Net amount: " + currentTransactionSummary.getNetAmount());
		        System.out.println("Payment method type: " + currentTransactionSummary.getPaymentMethodType());
		        System.out.println("Status: " + currentTransactionSummary.getStatus());
		        System.out.println("Type: " + currentTransactionSummary.getType());
		    }
		}
		
		return result;
	}

    private static void printTransaction(Transaction transaction) {

        System.out.println("code: " + transaction.getCode());
        System.out.println("date: " + transaction.getDate());
        System.out.println("discountAmount: " + transaction.getDiscountAmount());
        System.out.println("extraAmount: " + transaction.getExtraAmount());
        System.out.println("feeAmount: " + transaction.getFeeAmount());
        System.out.println("grossAmount: " + transaction.getGrossAmount());
        System.out.println("installmentCount: " + transaction.getInstallmentCount());
        System.out.println("itemCount: " + transaction.getItemCount());

        for (int i = 0; i < transaction.getItems().size(); i++) {
            System.out.println("item[" + (i + 1) + "]: " + transaction.getItems().get(i));
        }

        System.out.println("lastEventDate: " + transaction.getLastEventDate());
        System.out.println("netAmount: " + transaction.getNetAmount());
        System.out.println("paymentMethodType: " + transaction.getPaymentMethod().getCode().getValue());
        System.out.println("paymentMethodcode: " + transaction.getPaymentMethod().getType().getValue());
        System.out.println("reference: " + transaction.getReference());
        System.out.println("senderEmail: " + transaction.getSender().getEmail());

        if (transaction.getSender() != null) {
            System.out.println("senderPhone: " + transaction.getSender().getPhone());
        }

        if (transaction.getShipping() != null) {
            System.out.println("shippingType: " + transaction.getShipping().getType().getValue());
            System.out.println("shippingCost: " + transaction.getShipping().getCost());
            if (transaction.getShipping().getAddress() != null) {
                System.out.println("shippingAddressCountry: " + transaction.getShipping().getAddress().getCountry());
                System.out.println("shippingAddressState: " + transaction.getShipping().getAddress().getState());
                System.out.println("shippingAddressCity: " + transaction.getShipping().getAddress().getCity());
                System.out.println("shippingAddressPostalCode: "
                        + transaction.getShipping().getAddress().getPostalCode());
                System.out.println("shippingAddressDistrict: " + transaction.getShipping().getAddress().getDistrict());
                System.out.println("shippingAddressStreet: " + transaction.getShipping().getAddress().getStreet());
                System.out.println("shippingAddressNumber: " + transaction.getShipping().getAddress().getNumber());
                System.out.println("shippingAddressComplement: "
                        + transaction.getShipping().getAddress().getComplement());
            }
        }

        System.out.println("status: " + transaction.getStatus().getValue());
        System.out.println("type: " + transaction.getType().getValue());
    }
    
   
    private BigDecimal createBigDecimal(double value){
    	DecimalFormat df = (DecimalFormat) DecimalFormat.getCurrencyInstance(Locale.ENGLISH);
    	df.applyPattern("#,##0.00");
    	String format = df.format(value);
   	    BigDecimal bigValue = new BigDecimal(format);
        	
        return bigValue;
    	
    }
    
}
