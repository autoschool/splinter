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
package ru.yandex.autoschool.splinter.forms;

import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.HtmlElement;
import ru.yandex.qatools.htmlelements.element.TextInput;

/**
 *
 * @author Ksenia Nenasheva <ks.nenasheva@gmail.com>
 */
public class NewPostForm extends HtmlElement{

  @FindBy(id = "title")
  private TextInput title;

  @FindBy(id = "body")
  private TextInput body;

  @FindBy(id = "submit")
  private Button submitBtn;

  public TextInput getTitle() {
    return this.title;
  }

  public TextInput getBody() {
    return this.body;
  }

  public Button getSubmitBtn() {
    return this.submitBtn;
  }
  
  public void CreateNewPost(String title, String body) {
    getTitle().sendKeys(title);
    getBody().sendKeys(body);
    getSubmitBtn().click();
  }
}
