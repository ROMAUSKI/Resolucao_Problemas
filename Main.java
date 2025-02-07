import java.util.HashMap;
import java.util.Map;

// Singleton - Gerenciador de Conexões com o Banco de Dados
class DatabaseConnection {
    private static DatabaseConnection instance;
    private String connection;

    private DatabaseConnection() {
        this.connection = "Conexão com o banco de dados estabelecida.";
    }

    public static synchronized DatabaseConnection getInstance() {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }

    public String getConnection() {
        return connection;
    }
}

// Factory Method - Criador de Relatórios
interface Report {
    String generate();
}

class PDFReport implements Report {
    public String generate() {
        return "Relatório em PDF gerado.";
    }
}

class CSVReport implements Report {
    public String generate() {
        return "Relatório em CSV gerado.";
    }
}

class ReportFactory {
    private static final Map<String, Report> reportCache = new HashMap<>();
    
    public static Report createReport(String reportType) {
        return reportCache.computeIfAbsent(reportType, type -> {
            switch (type) {
                case "PDF": return new PDFReport();
                case "CSV": return new CSVReport();
                default: throw new IllegalArgumentException("Tipo de relatório desconhecido");
            }
        });
    }
}

// Testando a implementação
public class Main {
    public static void main(String[] args) {
        DatabaseConnection db1 = DatabaseConnection.getInstance();
        DatabaseConnection db2 = DatabaseConnection.getInstance();
        System.out.println(db1.getConnection()); // Singleton em ação (mesma instância)
        System.out.println(db1 == db2); // Deve ser true

        Report report1 = ReportFactory.createReport("PDF");
        System.out.println(report1.generate());

        Report report2 = ReportFactory.createReport("CSV");
        System.out.println(report2.generate());
    }
}
