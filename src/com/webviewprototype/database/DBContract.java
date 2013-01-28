package com.webviewprototype.database;

import android.provider.BaseColumns;

public abstract class DBContract {
	
	public static final String DATABASE_NAME = "CharityDB.db";
	public static final Integer VERSION = 1;
	
	private DBContract() {}
	
	public static abstract class FormTable implements BaseColumns {
		public static final String TABLE_NAME = "Form";

		public static final String COLUMN_NAME_FORM_ID = "Form_Id";
		public static final String COLUMN_NAME_FORM_TYPE_ID = "Form_Type_ID";
		public static final String COLUMN_NAME_FORM_NAME = "Form_Name";
		public static final String COLUMN_NAME_DATE_CREATED = "Date_Created";
		public static final String COLUMN_NAME_URL = "URL";
		public static final String COLUMN_NAME_IS_ACTIVE = "isActive";
		public static final String COLUMN_NAME_TIMESTAMP = "Timestamp";
	}
	
	public static abstract class FormFields implements BaseColumns {
		public static final String TABLE_NAME = "Form_Fields";

		public static final String COLUMN_NAME_FIELD_ID = "Field_Id";
		public static final String COLUMN_NAME_FORM_ID = FormTable.COLUMN_NAME_FORM_ID;
		public static final String COLUMN_NAME_FIELD_LABEL = "Field_Label";
		public static final String COLUMN_NAME_FIELD_TYPE_ID = FieldType.COLUMN_NAME_FIELD_TYPE_ID;
		public static final String COLUMN_NAME_FIELD_SELECTION_ID = "Field_Selection_Id";
		public static final String COLUMN_NAME_X_COORDINATE = "X_coordinate";
		public static final String COLUMN_NAME_Y_COORDINATE = "Y_coordinate";
		public static final String COLUMN_NAME_IS_REQUIRED = "isRequired";
		public static final String COLUMN_NAME_DEFAULT_VALUE = "Default_Value";
		public static final String COLUMN_NAME_MIN_VALUE = "Min_Value";
		public static final String COLUMN_NAME_MAX_VALUE = "Max_Value";
		public static final String COLUMN_NAME_USER_ID = "User_Id";
		public static final String COLUMN_NAME_DATE_CREATED = "Date_Created";
		public static final String COLUMN_NAME_IS_ACTIVE = "isActive";
		public static final String COLUMN_NAME_TIMESTAMP = "Timestamp";
	}
	
	public static abstract class FieldType implements BaseColumns {
		public static final String TABLE_NAME = "Field_Type";

		public static final String COLUMN_NAME_FIELD_TYPE_ID = "Field_Type_Id";
		public static final String COLUMN_NAME_FIELD_TYPE = "Field_Type";
		public static final String COLUMN_NAME_FIELD_DATA_TYPE = "Field_DataType";
		public static final String COLUMN_NAME_FIELD_DESCRIPTION = "Field_Description";
	}
	
	public static abstract class FilledForm implements BaseColumns {
		public static final String TABLE_NAME = "Filled_Form";
		
		public static final String COLUMN_NAME_FILLED_FORM_ID = "Filled_Form_Id";
		public static final String COLUMN_NAME_FORM_ID = FormTable.COLUMN_NAME_FORM_ID;
		public static final String COLUMN_NAME_FIELD_ID = FormFields.COLUMN_NAME_FIELD_ID;
		public static final String COLUMN_NAME_VALUE = "Value";
		public static final String COLUMN_NAME_USER_ID = FormFields.COLUMN_NAME_USER_ID;
		public static final String COLUMN_NAME_RECORD_ID = "Record_Id";
		public static final String COLUMN_NAME_IS_ACTIVE = "isActive";
		public static final String COLUMN_NAME_TIMESTAMP = "Timestamp";
	}
}
