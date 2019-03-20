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
import model.Product;
import model.Shoe;
import model.ShoeType;

public class AppController implements Initializable {

	Logger logger = Logger.getLogger(AppController.class.getName());

	@FXML
	private ListView<String> list;

	private ArrayList<Product> database = new ArrayList<>();

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
	private void printProducts() {
		ObservableList<String> items = FXCollections.observableArrayList();

		for (Product product : database) {
			if (product.isLessThan(30)) {
				items.add(String.valueOf(product.getName() + " (" + product.getProductType() +")" + ": " + product.getPrice() + " UAH"));
			}
		}

		list.setItems(items);

		logger.log(Level.INFO, "Вывод всех депозитов");
	}

	@FXML
	private void printProductsLt30(){
		ObservableList<String> items = FXCollections.observableArrayList();

		for (Product product : database) {
			if (product.isLessThan(30)) {
				items.add(product.getName() + " (Price: " + product.getPrice() + "UAH, Discount: " + product.getDiscount() + ")");
			}
		}

		list.setItems(items);

		logger.log(Level.INFO, "Вывод всех товаров");
	}

	@FXML
	private void printAutumnDiscount() {
		ObservableList<String> items = FXCollections.observableArrayList();

		for (Product product : database) {
			if (product instanceof Shoe && ((Shoe) product).getType() == ShoeType.Summer && product.getDiscount() > 0) {
				items.add(product.getName() + " (Price: " + product.getPrice() + "UAH, Discount: " + product.getDiscount() + ")");
			}
		}

		list.setItems(items);

		logger.log(Level.INFO, "Вывод всех товаров");
	}

	@FXML
	private void showMessage() {
		JOptionPane.showMessageDialog(null, "Окно помощи");
	}

	private void generate() {
		logger.log(Level.INFO, "Генерация данных началась.");
		String[] shoes = { "Sleeper", "Wellington Boots", "Mules", "Heels", "Court Shoes", "Lace Ups", "Canvas Shoes", "Flip Flops", "Loafers" };

		for (int i = 0; i < 100; i++) {
			Random r = new Random();
			String shoe = shoes[r.nextInt(shoes.length)];
			Product product = new Shoe(shoe, ShoeType.values()[r.nextInt(4)], r.nextInt(99) + 1, r.nextInt(6) * 5);
			database.add(product);
			logger.log(Level.INFO, "Добавлен продукт: " + product.getName() + "(" + product.getPrice() + " UAH)");
		}
		logger.log(Level.INFO, "Генерация данных завершена.");
	}

}
