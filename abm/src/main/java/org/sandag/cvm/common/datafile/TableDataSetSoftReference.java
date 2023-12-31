/*
 * Created on 2-Feb-2006
 *
 * Copyright  2005 JE Abraham and others
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

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

public class TableDataSetSoftReference extends SoftReference {
    
    String name;

    public TableDataSetSoftReference(TableDataSet me) {
        super(me);
        name = me.getName();
    }

    public TableDataSetSoftReference(TableDataSet me, ReferenceQueue q) {
        super(me, q);
        name = me.getName();
    }

}
