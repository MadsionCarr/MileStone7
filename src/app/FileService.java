package app;

import java.io.File;
import java.io.IOException;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
//import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Handles loading and saving inventory data to and from JSON files. Uses
 * Jackson to serialize and deserialize SalableProduct objects.
 */
public class FileService {

	/** ObjectMapper used for JSON processing */
	private ObjectMapper objectMapper;

	public FileService() {

		objectMapper = new ObjectMapper();
		objectMapper.findAndRegisterModules();

		// ⭐ Workaround for Jackson 2.10.x + Java modules
		objectMapper.activateDefaultTyping(objectMapper.getPolymorphicTypeValidator(),
				ObjectMapper.DefaultTyping.NON_FINAL, JsonTypeInfo.As.PROPERTY);

	}

	/**
	 * * Loads inventory from a JSON file. * * @param file the JSON file to load
	 * * @return a list of SalableProduct objects * @throws InventoryFileException
	 * if the file cannot be read or parsed
	 */

	@SuppressWarnings("unchecked")
	public List<SalableProduct> loadInventory(File file) throws InventoryFileException {
		try {
			Object data = objectMapper.readValue(file, Object.class);
			// Jackson default typing wraps lists like:
			// [ "java.util.ArrayList", [ ...items... ] ]
			if (data instanceof List) {
				List<?> wrapper = (List<?>) data;
				if (wrapper.size() == 2 && wrapper.get(1) instanceof List) {
					return (List<SalableProduct>) wrapper.get(1);
				}
			} // Fallback: maybe Jackson wrote a plain list
			return (List<SalableProduct>) data;
		} catch (Exception e) {
			throw new InventoryFileException("Error reading JSON file: " + file.getAbsolutePath(), e);
		}
	}


	/**
	 * Loads inventory from a JSON file using the given filename.
	 *
	 * @param filename the name of the JSON file to load
	 * @return a list of SalableProduct objects
	 * @throws InventoryFileException if the file cannot be read or parsed
	 */
	public List<SalableProduct> loadInventoryFromFilename(String filename) throws InventoryFileException {
		try {
			File file = new File(filename); // debug line
			System.out.println("DEBUG: Loading inventory from: " + file.getAbsolutePath());
			return loadInventory(file);
		} catch (Exception e) {
			throw new InventoryFileException("Failed to load inventory from filename: " + filename, e);
		}
	}

	/**
	 * * Saves the given list of products to a JSON file. * * @param filename the
	 * name of the file to save to * @param products the list of products to save
	 * * @throws InventoryFileException if the file cannot be written
	 */

	public void saveInventory(String filename, List<SalableProduct> products) throws InventoryFileException {
		try {
			File file = new File(filename);
			objectMapper.writerWithDefaultPrettyPrinter().writeValue(file, products);
		} catch (IOException e) {
			throw new InventoryFileException("Error saving inventory to file: " + filename, e);
		}
	}
}
