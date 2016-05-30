<#include "./header.ftl">
<div class="container">
    <#if delete??>
        <div class="row">
            <div class="col-xs-12">
                <#if delete == "success">
                    <div class="alert alert-success">
                        Estudiante borrado exitosamente!
                    </div>
                <#else>
                    <div class="alert alert-danger">
                        Error borrando estudiante!
                    </div>
                </#if>
            </div>
        </div>
    </#if>
    <div class="row">
        <div class="col-xs-3 col-xs-offset-9">
            <a href="/add" class="btn btn-success btn-block">
                <i  class="fa fa-plus"></i>
                Agregar Estudiante
            </a>
        </div>
    </div>
    <div class="row">
        <div class="col-xs-12">
            <table class="table table-responsive table-striped">
                <thead>
                <th>Nombre</th>
                <th>Matricula</th>
                <th>Accion</th>
                </thead>
                <tbody>
                <#list students as student>
                <tr>
                    <td>${student.getNombreCompleto()}</td>
                    <td>${student.getMatricula()?string.computer}</td>
                    <td>
                        <div class="btn-group">
                            <button type="button" class="btn btn-info dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                Menu <i class="fa fa-caret-down" ></i>
                            </button>
                            <ul class="dropdown-menu">
                                <li><a href="/view/${student.getMatricula()?string.computer}"><i class="fa fa-user"></i> Ver</a></li>
                                <li><a href="/edit/${student.getMatricula()?string.computer}"><i class="fa fa-pencil"></i> Editar</a></li>
                                <li><a href="/delete/${student.getMatricula()?string.computer}"><i class="fa fa-exclamation-triangle"></i> Eliminar</a></li>
                            </ul>
                        </div>
                    </td>
                </tr>
                </#list>
                </tbody>
            </table>
        </div>
    </div>
</div>
<#include "./footer.ftl">