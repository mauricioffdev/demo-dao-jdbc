package application;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Program {

    static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        SellerDao sellerDao = DaoFactory.createSellerDao();

        System.out.println("=== TEST 1: seller findById =====");
        Seller seller = sellerDao.findById(3);
        System.out.println(seller);

        System.out.println("\n=== TEST 2: seller findByDepartment =====");
        Department department = new Department(2, null);
        List<Seller> list = sellerDao.findByDepartment(department);
        for (Seller obj : list) {
            System.out.println(obj);
        }

        System.out.println("\n=== TEST 3: seller findAll =====");
        list = sellerDao.findAll();
        for (Seller obj : list) {
            System.out.println(obj);
        }

            /*System.out.println("\n=== TEST 4: seller insert =====");
            Seller newSeller = new Seller(null, "Greg", "greg@gmail.com", new Date(), 4000.0, department);
            sellerDao.insert(newSeller);
            System.out.println("Inserted! New id = " + newSeller.getId());*/

        System.out.println("\n=== TEST 4: seller insert Clark & Lois =====");

// Define o departamento (ex: ID 1) sem redeclarar a variável
        department = new Department(1, null);

// --- Inserindo Clark Kent ---
// ID null, Nome, Email, Data Atual, Salário, Departamento
        Seller clark = new Seller(null, "Clark Kent", "clark@dailyplanet.com", new java.util.Date(), 5000.0, department);
        sellerDao.insert(clark);
        System.out.println("Inserted! Clark ID = " + clark.getId());

// --- Inserindo Lois Lane ---
        Seller lois = new Seller(null, "Lois Lane", "lois@dailyplanet.com", new java.util.Date(), 6000.0, department);
        sellerDao.insert(lois);
        System.out.println("Inserted! Lois ID = " + lois.getId());


        System.out.println("\n=== TEST 5: seller update =====");
        seller = sellerDao.findById(1);
        seller.setName("Martha Waine");
        sellerDao.update(seller);
        System.out.println("Update completed");

        System.out.println("\n=== TEST 6: seller delete range =====");

        System.out.print("Digite o ID inicial: ");
        int idInicio = scanner.nextInt();

        System.out.print("Digite o ID final: ");
        int idFim = scanner.nextInt();

        System.out.println("Apagando do " + idInicio + " até o " + idFim + "...");

        for (int i = idInicio; i <= idFim; i++) {
            try {
                sellerDao.deleteById(i);
                // Opcional: imprimir progresso visual
                // System.out.println("ID " + i + " apagado.");
            } catch (Exception e) {
                // Se der erro em um ID específico (ex: chave estrangeira), ele avisa e continua para o próximo
                System.out.println("Erro ao apagar o ID " + i + ": " + e.getMessage());
            }
        }

        System.out.println("Processo finalizado!");

        scanner.close();
    }
}
