/*
 * Copyright  2005 PB Consult Inc.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */
package org.sandag.cvm.common.datafile;

import java.io.IOException;

/**
 * Base class for classes which load table data from external data sources.
 *
 * @author   Tim Heier
 * @version  1.0, 2/07/2004
 *
 */

abstract public class TableDataWriter {

    public static final String CSV_WRITER = "CSVFile";
    public static final String JDBC_WRITER = "JDBCTable";
    public static final String BINARY_WRITER = "BinaryFile";

    
    abstract public void writeTable(TableDataSet tableData, String tableName) throws IOException ;
    //abstract public TableDataSet loadTable(String tableName, String queryString);

    
    public static TableDataWriter createWriter(String name) {
        Class clazz = null;
        try {
            clazz = Class.forName(name+"Writer");
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Object obj = null;
        try {
            obj = clazz.newInstance();
        }
        catch (InstantiationException e) {
            e.printStackTrace();
        }
        catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        
        return (TableDataWriter) obj;
    }
    /**
     * 
     */
    public abstract void close();

}
