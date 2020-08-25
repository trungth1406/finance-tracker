package com.self.learn.writer.impl;

import com.self.learn.writer.base.Exporter;
import com.self.learn.transaction.dto.TransactionMetaData;
import com.self.learn.transaction.dto.Transactions;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.InputStream;
import java.time.LocalDate;

public class XmlExporter implements Exporter<InputStream> {

    private static JAXBContext context;

    public XmlExporter() {
        initJaxb();
    }

    static void initJaxb() {
        try {
            context = JAXBContext.newInstance(Transactions.class);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void export(InputStream is) {

    }

    //    @Override
//    public void export(TransactionMetaData content) {
//        File file = new File(String.format("daily-transaction-%s.xml", LocalDate.now().toString()));
//        try {
//            Marshaller marshaller = setUpMarshaller();
//            Transactions existingTransactions = getExistingTransactions(file);
//            existingTransactions.getTransactionMetaDataList().add(content);
//            marshaller.marshal(existingTransactions, file);
//        } catch (JAXBException e) {
//            e.printStackTrace();
//        }
//    }

    private static Transactions getExistingTransactions(File file) throws JAXBException {
        Transactions transactions = null;
        if (file.length() > 0) {
            Unmarshaller unmarshaller = context.createUnmarshaller();
            transactions = (Transactions) unmarshaller.unmarshal(file);
        } else {
            transactions = new Transactions();
        }
        return transactions;
    }

    private static Marshaller setUpMarshaller() throws JAXBException {
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        return marshaller;
    }
}
