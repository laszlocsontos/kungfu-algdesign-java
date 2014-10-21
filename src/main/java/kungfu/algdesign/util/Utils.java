/*
 * Copyright (C) 2014 - present, Laszlo Csontos
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * 
 */

package kungfu.algdesign.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lcsontos
 */
public class Utils {

  private Utils() {
  }

  public static InputStream getInputStreamForResource(String name) {
    ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

    return classLoader.getResourceAsStream(name);
  }

  public static Integer[] getIntegerArray(InputStream inputStream) throws IOException {
    InputStreamReader inputStreamReader = new InputStreamReader(inputStream);

    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

    List<Integer> integerList = new ArrayList<>();

    String line = null;

    while ((line = bufferedReader.readLine()) != null) {
      integerList.add(Integer.valueOf(line));
    }

    Integer[] integerArray = new Integer[integerList.size()];

    return integerList.toArray(integerArray);
  }
}
