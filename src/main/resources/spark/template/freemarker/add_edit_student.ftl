<#include "./header.ftl">
<div class="container">
    <#if message??>
        <div class="row">
            <div class="col-xs-12">
                <div class="alert alert-danger">${message}</div>
            </div>
        </div>
    </#if>
    <div class="row">
        <form class="form" method="post">
            <div class="col-xs-6">
                <div class="form-group">
                    <label for="nombre">Nombres</label>
                    <input type="text" class="form-control"
                           value="<#if student??>${student.getNombre()}</#if>"
                           id="nombre" name="name" placeholder="ej. Juan">
                </div>
            </div>

            <div class="col-xs-6">
                <div class="form-group">
                    <label for="apellido">Apellidos</label>
                    <input type="text" class="form-control"
                           value="<#if student??>${student.getApellido()}</#if>"
                           id="apellido" name='lastName' placeholder="ej. Perez">
                </div>
            </div>

            <div class="col-xs-6">
                <div class="form-group">
                    <label for="matricula">Matricula</label>
                    <input type="text" class="form-control"
                           value="<#if student??>${student.getMatricula()?string.computer}</#if>"
                           id="matricula" name="mat" placeholder="ej. 20120178">
                </div>
            </div>

            <div class="col-xs-6">
                <div class="form-group">
                    <label for="telefono">Telefono</label>
                    <input type="text" class="form-control"
                           value="<#if student??>${student.getTelefono()}</#if>"
                           id="telefono" name="tel" placeholder="ej. 809-897-5132">
                </div>
            </div>
            <div class="col-xs-6">
                <a href="/" class="pull-right btn btn-info btn-block">Cancelar</a>
            </div>
            <div class="col-xs-6">
                <#if is_edit??>
                    <button class="pull-right btn btn-success btn-block"><i class="fa fa-pencil"></i> Finalizar Edicion</button>
                <#else>
                    <button class="pull-right btn btn-success btn-block"><i class="fa fa-plus"></i> Agregar</button>
                </#if>
            </div>
        </form>
    </div>
</div>
<#include "./footer.ftl">