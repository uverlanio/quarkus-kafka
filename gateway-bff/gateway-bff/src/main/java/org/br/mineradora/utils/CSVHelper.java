package org.br.mineradora.utils;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.br.mineradora.dto.OpportunityDTO;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

public class CSVHelper {

    public static ByteArrayInputStream OpportunitiesToCSV(List<OpportunityDTO> opportunities) throws Exception {
        final CSVFormat format = CSVFormat.DEFAULT.withHeader(
                "Id Proposta",
                "Cliente",
                "Preco por Tonelada",
                "Melhor Cotacao de Moeda"
        );

        try{
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(out), format);
                for(OpportunityDTO opps: opportunities){
                    List<String> data = Arrays.asList(
                            String.valueOf(opps.getProposalId()),
                            opps.getCustomer(),
                            String.valueOf(opps.getPriceTonne()),
                            String.valueOf(opps.getLastDollarQuotation())
                    );
                    csvPrinter.print(data);
                }

            csvPrinter.flush();

            return new ByteArrayInputStream(out.toByteArray());

            }catch (RuntimeException e){
                throw new Exception("Não foi possível criar o arquivo CSV, causa: " + e.getMessage());
        }
    }
}
