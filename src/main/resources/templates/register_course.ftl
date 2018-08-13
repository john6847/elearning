<!DOCTYPE HTML>
<html xmlns:layout="http://www.ultraq.net.nz/thymeleaf/layout" layout:decorator="layout/layout">
<head>
    <title>Register Course</title>
</head>
<body>
<div layout:fragment="content">
    <div class="container" ng-controller="CourseController as rc_ctrl">

        <md-card md-theme="{{ showDarkTheme ? 'dark-grey' : 'default' }}" md-theme-watch="">
            <md-content class="md-padding" layout="column" layout-xs="row">

                <div class="alert alert-danger" role="alert" ng-show="message">{{message}}</div>
                <form name="courseForm">
                    <md-card-title>
                        <md-card-title-text>
                            <span class="md-headline">Ajouter un nouveau cours</span>
                        </md-card-title-text>
                    </md-card-title>
                    <hr>
                    <input type="hidden" ng-model="rc_ctrl.course.id">
                    <div class="row form-group">
                        <div class="col-md-6 col-xs-12">
                            <label class="control-label" for="title">Titre</label>
                            <input type="text"
                                   submit-required="true"
                                   id="title"
                                   class="form-control"
                                   name="title"
                                   title="Entrez Le titre du cours"
                                   ng-minlength="2"
                                   ng-maxlength="100"
                                   ng-model="rc_ctrl.course.title"
                                   placeholder="Le titre du cours"
                                   required>
                            <span style="color: red;"
                                  ng-if="courseForm.title.$error.maxlength && (courseForm.title.$dirty ||  courseForm.title.$touched)">Titre trop long!</span>
                            <span style="color: red;"
                                  ng-if="courseForm.title.$invalid && (courseForm.title.$dirty ||  courseForm.title.$touched)">Titre Invalide</span>
                            <span style="color: red;"
                                  ng-if="courseForm.title.$error.required &&  (courseForm.title.$dirty ||  courseForm.title.$touched)">Titre obligatoire!</span>
                            <span style="color: red;"
                                  ng-if="courseForm.title.$error.minlength && (courseForm.title.$dirty ||  courseForm.title.$touched)">Titre trop court!</span>
                        </div>
                        <div class="col-md-6 col-x-12">
                            <label class="control-label" for="price">Prix</label>
                            <input type="text"
                                   ng-decimal="15"
                                   submit-required="true"
                                   id="price"
                                   class="form-control"
                                   name="price"
                                   title="Entrez Le prix du cours"
                                   ng-minlength="1"
                                   ng-model="rc_ctrl.course.price"
                                   placeholder="Le prix du cours en dolar"
                                   pattern="\d+(\.\d{1,2})?"
                                   required>
                            <span style="color: red;"
                                  ng-if="courseForm.price.$invalid && (courseForm.price.$dirty ||  courseForm.price.$touched)">Prix Invalide!! </span>
                            <span style="color: red;"
                                  ng-if="courseForm.price.$error.number && (courseForm.price.$dirty ||  courseForm.price.$touched)">Prix error!! </span>
                            <span style="color: red;"
                                  ng-if="courseForm.price.$error.required && (courseForm.price.$dirty ||  courseForm.price.$touched) ">Prix obligatoire!!  </span>
                            {{rc_ctrl.course.price}}
                        </div>
                    </div>

                    <div class="row form-group">
                        <div class="col-md-6 col-xs-12">
                            <label class="control-label" for="category">Catégorie</label>
                            <select class="form-control"
                                    title="Choisit une catégorie"
                                    name="category"
                                    ng-model="rc_ctrl.course.category"
                                    ng-options="category.name for category in rc_ctrl.categories track by category.id"
                                    id="category"
                                    required>
                                <option value="">--Choisit une catégorie--</option>
                            </select>
                            <span style="color: red;"
                                  ng-show="courseForm.category.$error.required && (courseForm.category.$dirty || courseForm.category.$touched)">Catégory requis!</span>
                            <span style="color: red;"
                                  ng-show="courseForm.category.$invalid && (courseForm.category.$dirty || courseForm.category.$touched)">Catégory invalide!</span>
                        </div>
                        <div class="col-md-6 col-xs-12">
                            <label class="control-label" for="startDate">Début du cours</label>
                            <input type="date" submit-required="true" data-date-format="dd/MMMM/yyyy"
                                   class="form-control" id="startDate" ng-model="rc_ctrl.course.startDate"
                                   placeholder="Debut du Cours" title="Choisit la dâte du debut" required>
                            <span style="color: red;"
                                  ng-show="myForm.startDate.$error.required && (courseForm.startDate.$dirty ||  courseForm.startDate.$touched)">Début du cours requis</span>
                            <span style="color: red;"
                                  ng-show="myForm.startDate.$invalid && (courseForm.startDate.$dirty ||  courseForm.startDate.$touched)">Date Invalide</span>
                        </div>
                    </div>

                    <div class="row form-group">
                        <div class="col-md-6 col-xs-12">
                            <label class="control-label" for="duration">Duration</label>
                            <input type="text"
                                   numbers-only
                                   submit-required="true"
                                   id="duration"
                                   class="form-control"
                                   name="duration"
                                   title="Entrez la durée du cours"
                                   ng-minlength="1"
                                   ng-maxlength="100"
                                   ng-model="rc_ctrl.course.duration"
                                   placeholder="La durée du cours en semaine"
                                   pattern="[0-9]+"
                                   required>
                            <span style="color: red;"
                                  ng-if="courseForm.duration.$invalid && (courseForm.duration.$dirty ||  courseForm.duration.$touched)">Prix Invalide!</span>
                            <span style="color: red;"
                                  ng-if="courseForm.duration.$error.required && (courseForm.duration.$dirty ||  courseForm.duration.$touched) ">Prix obligatoire!</span>
                        </div>
                        <div class="col-md-6 col-xs-12">
                            <label for="premium" class="control-label">Est premium</label>
                            <md-switch class="md-primary" name="premium" ng-model="rc_ctrl.course.premium">
                                Est premium.
                            </md-switch>
                        </div>
                    </div>
                    <h4>Syllabus</h4>
                    <div class="row form-group">
                        <div class="col-md-12">
                            <label class="control-label" for="about">A propos du cours</label>
                            <textarea name="about"
                                      submit-required="true"
                                      id="about"
                                      cols="40"
                                      rows="10"
                                      class="form-control"
                                      title="Definir le syllabus du cours"
                                      ng-model="rc_ctrl.course.syllabus.about"
                                      placeholder="Syllabus du cours"
                                      required></textarea>
                            <span style="color: red;"
                                  ng-if="courseForm.about.$error.maxlength && (courseForm.about.$dirty ||  courseForm.about.$touched)">Syllabus trop long!</span>
                            <span style="color: red;"
                                  ng-if="courseForm.about.$invalid && (courseForm.about.$dirty ||  courseForm.about.$touched)">Syllabus Invalide</span>
                            <span style="color: red;"
                                  ng-if="courseForm.about.$error.required &&  (courseForm.about.$dirty ||  courseForm.about.$touched)">Syllabus obligatoire!</span>
                            <span style="color: red;"
                                  ng-if="courseForm.about.$error.minlength && (courseForm.about.$dirty ||  courseForm.about.$touched)">Syllabus trop court!</span>
                        </div>
                    </div>
                    <md-card-actions layout="row" layout-align="end center">
                        <md-button type="reset" class="md-raised md-warn" ng-click="rc_ctrl.reset()">Annuler</md-button>
                        <md-button type="submit" class="md-raised md-primary" ng-disabled="courseForm.$invalid"
                                   ng-click="rc_ctrl.register()">Enregistrer
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

