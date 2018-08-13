<!DOCTYPE HTML>
<html xmlns:layout="http://www.ultraq.net.nz/thymeleaf/layout" layout:decorator="layout/layout">
<head>
    <title>Category</title>
</head>
<body>
<div layout:fragment="content">
    <div class="container" ng-controller="CategoryController as cat_ctrl">
        <md-card md-theme="{{ showDarkTheme ? 'dark-grey' : 'default' }}" md-theme-watch="">
            <md-content class="md-padding" layout="column" layout-xs="row">

                <div class="alert alert-danger" role="alert" ng-show="message">{{message}}</div>
                <form name="categoryForm">
                    <md-card-title>
                        <md-card-title-text>
                            <span class="md-headline">Ajouter une catégorie</span>
                        </md-card-title-text>
                    </md-card-title>
                    <hr>
                    <input type="hidden" ng-model="cat_ctrl.category.id">
                    <div class="row form-group">
                        <div class="col-md-12 col-xs-12">
                            <label class="control-label" for="name">Nom</label>
                            <input type="text"
                                   submit-required="true"
                                   id="name"
                                   class="form-control"
                                   name="name"
                                   title="Nom catégorie"
                                   ng-minlength="2"
                                   ng-maxlength="100"
                                   ng-model="cat_ctrl.category.name"
                                   placeholder="Nom catégorie..."
                                   required>
                            <span style="color: red;"
                                  ng-if="categoryForm.name.$invalid && (categoryForm.name.$dirty ||  categoryForm.name.$touched)">Nom Invalide  </span>
                            <span style="color: red;"
                                  ng-if="categoryForm.name.$error.required &&  (categoryForm.name.$dirty ||  categoryForm.name.$touched)">Nom obligatoire!  </span>
                            <span style="color: red;"
                                  ng-if="categoryForm.name.$error.maxlength && (categoryForm.name.$dirty ||  categoryForm.name.$touched)">Nom trop long!  </span>
                            <span style="color: red;"
                                  ng-if="categoryForm.name.$error.minlength && (categoryForm.name.$dirty ||  categoryForm.name.$touched)">Nom trop court!  </span>
                        </div>
                    </div>

                    <div class="row form-group">
                        <div class="col-md-12">
                            <label class="control-label" for="description">Déscription</label>
                            <textarea
                                    name="description"
                                    submit-required="true"
                                    id="description"
                                    cols="40"
                                    rows="10"
                                    class="form-control"
                                    name="description"
                                    id="description"
                                    minlength="2"
                                    maxlength="250"
                                    title="Déscription du cours"
                                    ng-model="cat_ctrl.category.description"
                                    placeholder="Déscription du cours..."
                                    required></textarea>
                            <span style="color: red;"
                                  ng-if="categoryForm.description.$error.maxlength && (categoryForm.description.$dirty ||  categoryForm.description.$touched)">Description trop long!   </span>
                            <span style="color: red;"
                                  ng-if="categoryForm.description.$invalid && (categoryForm.about.$dirty ||  categoryForm.description.$touched)">Description Invalide    </span>
                            <span style="color: red;"
                                  ng-if="categoryForm.description.$error.required &&  (categoryForm.about.$dirty ||  categoryForm.description.$touched)">Description  obligatoire!  </span>
                            <span style="color: red;"
                                  ng-if="categoryForm.description.$error.minlength && (categoryForm.description.$dirty ||  categoryForm.description.$touched)">Description trop court!  </span>
                        </div>
                    </div>
                    <div class="row form-group">
                        <div class="col-md-12 col-xs-12">
                            <label class="control-label" for="meta-category">Meta-Catégorie</label>
                            <select class="form-control"
                                    submit-required="true"
                                    title="Choisit une meta catégorie"
                                    name="meta-category"
                                    ng-model="cat_ctrl.category.parentCategory"
                                    ng-options="category.name for category in cat_ctrl.categories track by category.id"
                                    id="category"
                            <#--ng-change="ctrl.categoryChanged(ctrl.selectedCine)" -->
                            >
                                <option value="">--Choisit une meta catégorie--</option>
                            </select>
                            <span style="color: red;"
                                  ng-show="categoryForm.category.$error.required && (categoryForm.category.$dirty ||  categoryForm.category.$touched)">Catégory requis!</span>
                        </div>
                    </div>
                    <div class="row form-group">
                        <div class="col-md-12 col-xs-12">
                            <label class="control-label" for="topic">Topic</label>
                            <md-chips ng-model="cat_ctrl.topics"
                                      readonly="false"
                                      md-transform-chip="cat_ctrl.newTopic($chip)"
                                      md-removable="true"
                                      placeholder="Entrez un topic"
                                      delete-button-label="Eliminer Topic"
                                      title="Entrez au moins une topique"
                                      delete-hint="Eliminer"
                                      secondary-placeholder="Entrez un nouveau topique"
                                      ng-required="true"
                                      name="topic">
                                <md-chip-template>
                                            <span>
                                              <strong>{{$chip.name}}</strong>
                                              <em>({{$chip.category.name}})</em>
                                            </span>
                                </md-chip-template>
                            </md-chips>

                            <span style="color: red;"
                                  ng-if="cat_ctrl.topics.length === 0 && (categoryForm.topic.$dirty ||  categoryForm.topic.$touched)">Entrez au moins une topique </span>
                        </div>
                    </div>

                    <md-card-actions layout="row" layout-align="end center">
                        <md-button type="reset" class="md-raised md-warn" ng-click="cat_ctrl.reset()">Annuler
                        </md-button>
                        <md-button type="submit" class="md-raised md-primary"
                                   ng-disabled="cat_ctrl.topics.length === 0
                               && categoryForm.name.$error
                               && categoryForm.description.$error" ng-click="cat_ctrl.register()">Enregistrer
                        </md-button>
                    </md-card-actions>
                </form>
    </div>

    </md-content>

    </md-card>
</div>
</div>
</body>
</html>


