package com.webviewprototype.database;

import java.util.Map;

public interface DB {
	/**
	 * Creates a Form in the Form table.
	 * Returns the row ID of the instance if successful, -1 otherwise.
	 * 
	 * @param formID
	 * @param formTypeID
	 * @param formName
	 * @param URL
	 * @param isActive
	 * @return 
	 */
	public long createForm(Integer formID, Integer formTypeID, 
			String formName, String URL, Boolean isActive);
	
	/**
	 * Creates a Form Field instance in the Form_Field table.
	 * Returns the row ID of the instance if successful, -1 otherwise.
	 * 
	 * @param fieldId
	 * @param formID
	 * @param fieldLabel
	 * @param fieldTypeID
	 * @param xCoord
	 * @param yCoord
	 * @param isRequired
	 * @param defaultValue
	 * @param minValue
	 * @param maxValue
	 * @param userID
	 * @param isActive
	 * @return
	 */
	public long createFormField(Integer fieldId, Integer formID,
			String fieldLabel, Integer fieldTypeID, Float xCoord, 
			Float yCoord, Boolean isRequired, Boolean defaultValue,
			Float minValue, Float maxValue, Integer userID,
			Boolean isActive);
	
	/**
	 * Creates a Field Type instance in the Field_Type table.
	 * Returns the row ID of the instance if successful, -1 otherwise.
	 * 
	 * @param fieldTypeID
	 * @param fieldType
	 * @param fieldDataType
	 * @param fieldDescription
	 * @return
	 */
	public long createFieldType(Integer fieldTypeID, String fieldType,
			String fieldDataType, String fieldDescription);
	
	/**
	 * Creates a Filled Form instance in the Filled_Form table.
	 * Returns the row ID of the instance if successful, -1 otherwise.
	 * 
	 * @param filledFormID
	 * @param formID
	 * @param fieldID
	 * @param value
	 * @param userID
	 * @param recordID
	 * @param isActive
	 * @return
	 */
	public long createFilledForm(Integer filledFormID, Integer formID,
			Integer fieldID, String value, Integer userID, Integer recordID,
			Boolean isActive);
	
	public Map<String, String> getForm(long rowID);
	
	public Map<String, String> getFormField(long rowID);
	
	public Map<String, String> getFieldType(long rowID);
	
	public Map<String, String> getFilledForm(long rowID);
}
