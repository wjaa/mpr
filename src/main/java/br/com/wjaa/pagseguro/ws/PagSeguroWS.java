package br.com.wjaa.pagseguro.ws;

import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.Date;

import br.com.uol.pagseguro.domain.Transaction;
import br.com.uol.pagseguro.domain.TransactionSearchResult;
import br.com.uol.pagseguro.exception.PagSeguroServiceException;
import br.com.wjaa.mpr.entity.Pedido;


public interface PagSeguroWS {

	URL criarPagamento(Pedido pedido) throws PagSeguroServiceException;
	
	Transaction notificacao(String notificationCode) throws PagSeguroServiceException;
	
	Transaction buscaTransactionByCode(String transactionCode) throws PagSeguroServiceException;
	
	TransactionSearchResult buscarTransacaoByDate(Date initialCalendar, Date finalCalendar) 
    		throws SecurityException, NoSuchFieldException, IllegalArgumentException,
    IllegalAccessException, NoSuchMethodException, InvocationTargetException, PagSeguroServiceException;
	
}
