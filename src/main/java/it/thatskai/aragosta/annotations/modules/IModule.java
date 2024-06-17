package it.thatskai.aragosta.annotations.modules;


import it.thatskai.aragosta.module.Category;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface IModule {

    /***
     * Name of the module
     * @return module name
     */
    String name();

    /***
     * Key of the module
     * @return module key
     */
    int key();

    /***
     * Category of the module
     * @return module category
     */
    Category category();

}
