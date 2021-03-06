/*
 * Copyright (C) 2013 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.nbandroid.netbeans.gradle.v2.project.template.freemarker.converters;

import com.android.utils.XmlUtils;
import freemarker.template.*;
import java.util.List;

/**
 * Method invoked by FreeMarker to escape a string such that it can be used as
 * an XML attribute (escaping ', ", & and <).
 */
public class FmEscapeXmlAttributeMethod implements TemplateMethodModelEx {

    @Override
    public TemplateModel exec(List args) throws TemplateModelException {
        if (args.size() != 1) {
            throw new TemplateModelException("Wrong arguments");
        }
        try {
            String string = ((TemplateScalarModel) args.get(0)).getAsString();
            return new SimpleScalar(XmlUtils.toXmlAttributeValue(string));
        } catch (Exception exception) {
            throw new TemplateModelException("Wrong arguments");
        }
    }
}
