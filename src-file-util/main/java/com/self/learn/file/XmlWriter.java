package com.self.learn.file;

import com.self.learn.file.base.Writer;
import com.self.learn.transaction.dto.TransactionMetaData;
import com.self.learn.transaction.dto.Transactions;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.time.LocalDate;

public class XmlWriter implements Writer<TransactionMetaData> {

    private static JAXBContext context;

    public XmlWriter() {
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
    public void write(TransactionMetaData content) {
        File file = new File(String.format("daily-transaction-%s.xml", LocalDate.now().toString()));
        try {
            Marshaller marshaller = setUpMarshaller();
            Transactions existingTransactions = getExistingTransactions(file);
            existingTransactions.getTransactionMetaDataList().add(content);
            marshaller.marshal(existingTransactions, file);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

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
