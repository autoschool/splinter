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
package ru.yandex.autoschool.splinter.steps;

import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.Link;

/**
 *
 * @author Ksenia Nenasheva <ks.nenasheva@gmail.com>
 */
public class Navigation {

  @FindBy(linkText = "allposts")
  private Link goToAllPosts;

  @FindBy(linkText = "authors")
  private Link goToAuthors;

  @FindBy(linkText = "login")
  private Link Login;
  
  @FindBy(linkText = "profile")
  private Link goToProfile;

  @FindBy(id = "newpost")
  private Link goToNewPost;

  @FindBy(linkText = "singout")
  private Link SingOut;

  public Link getGoToNewPost() {
    return this.goToNewPost;
  }
  
  public Link getGoToAllPosts() {
    return this.goToAllPosts;
  }
  
  public Link getGoToAuthors() {
    return this.goToAuthors;
  }

  public Link Login() {
    return this.Login;
  }

  public Link SingOut() {
    return this.SingOut;
  }
  
  public void openAuthorsPage() {
    getGoToAuthors().click();
  }

  public void openAllPostPage(){
    getGoToAllPosts().click();
  }
}
