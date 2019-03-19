package application;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import logging.MyFilter;
import logging.MyFormatter;
import logging.MyHandler;
import model.AccountOwner;

public class AppController implements Initializable {

	Logger logger = Logger.getLogger(AppController.class.getName());

	@FXML
	private ListView<String> list;

	private ArrayList<AccountOwner> database = new ArrayList<>();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		

		try {
			logger.setUseParentHandlers(false);
			MyHandler handler = new MyHandler("log.txt");
			handler.setFormatter(new MyFormatter());
			handler.setFilter(new MyFilter());
			logger.addHandler(handler);
		} catch (SecurityException | IOException e) {
			logger.log(Level.SEVERE, "Can't create the file log.", e);
		}

		
		
		generate();
	}

	@FXML
	private void sort() {

		String[] data = list.getItems().toArray(new String[list.getItems().size()]);

		int h = 1;
		while ((h * 3 + 1) < data.length)
			h = 3 * h + 1;
		while (h > 0) {
			for (int i = h - 1; i < data.length; i++) {
				String s = data[i];
				int j = i;
				for (j = i; (j >= h) && (data[j - h].compareTo(s) > 0); j -= h)
					data[j] = data[j - h];
				data[j] = s;
			}
			h /= 3;
		}

		list.getItems().clear();
		List<String> dataList = Arrays.asList(data);
		Collections.reverse(dataList);
		list.getItems().setAll(dataList);

		logger.log(Level.INFO, "Сортировка данных");
	}

	@FXML
	private void printDeposits() {
		ObservableList<String> items = FXCollections.observableArrayList();

		for (AccountOwner owner : database) {
			if (owner.getDeposit().isMoreThan(20_000)) {
				items.add(String.valueOf(owner.getDeposit().getAmount() + " UAH"));
			}
		}

		list.setItems(items);

		logger.log(Level.INFO, "Вывод всех депозитов");
	}

	@FXML
	private void printOwners() {
		ObservableList<String> items = FXCollections.observableArrayList();

		for (AccountOwner owner : database) {
			if (owner.getDeposit().isMoreThan(0)) {
				items.add(owner.getName() + "(" + owner.getDeposit() + ")");
			}
		}

		list.setItems(items);

		logger.log(Level.INFO, "Вывод всех владельцев");
	}

	@FXML
	private void showMessage() {
		JOptionPane.showMessageDialog(null, "Вiкно допомога");
	}

	private void generate() {
		logger.log(Level.INFO, "Генерация данных началась.");
		String[] firstnames = { "Alex", "John", "David", "Dmitriy", "Ivan", "Smith", "Eva", "Emily", "Kate" };
		String[] lastnames = { "Black", "Brown", "Adams", "Cole", "Slithy", "Dove" };

		for (int i = 0; i < 100; i++) {
			Random r = new Random();
			String firstname = firstnames[r.nextInt(firstnames.length)];
			String lastname = lastnames[r.nextInt(lastnames.length)];
			double creditAmount = r.nextInt(10) * 10000;
			double depositAmount = r.nextInt(10) * 10000;
			AccountOwner accountOwner = new AccountOwner(depositAmount, firstname, lastname);
			database.add(accountOwner);
			logger.log(Level.INFO, "Добавлен пользователь: " + accountOwner.getName() + "(" + accountOwner.getDeposit() + ")");
		}
		logger.log(Level.INFO, "Генерация данных завершена.");
	}

}
