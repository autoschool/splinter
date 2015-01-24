/*
 * Copyright 2015 Ksenia Nenasheva <ks.nenasheva@gmail.com>.
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

package ru.yandex.autoschool.splinter.IndexResources;
import org.junit.Test;
import ru.yandex.autoschool.splinter.utils.WebDriverRule;

import org.junit.Rule;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;


public class PageTitleTest {
  
  public WebDriver driver = new PhantomJSDriver();

    @Rule
    public WebDriverRule driverRule = new WebDriverRule(driver);

    public String baseUrl = driverRule.getBaseUrl();
    
    public void testPageTitle(String url, String ER){
      driver.get(url);
      String title = driver.getTitle();
      assertThat(title, equalTo(ER));
    }
    
    @Test
    public void testAboutPageTitle() {
      testPageTitle(baseUrl.concat("/about"), "Blog: About page");
    }
    
    @Test
    public void testIndexPageTitle() {
      testPageTitle(baseUrl, "Blog: posts");
    }
    
    @Test
    public void testNewPostPageTitle() {
      testPageTitle(baseUrl.concat("/posts/new"), "Blog: create new post");
    }
    
    @Test
    public void testAuthorsPageTitle() {
      testPageTitle(baseUrl.concat("/users"), "Blog: authors list");
    }
}
