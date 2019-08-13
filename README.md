# openfaas-templates

Custom templates for [OpenFaaS](https://www.openfaas.com).

# Usage

## Download the OpenFaaS templates

```
    faas template pull https://github.com/nlochschmidt/openfaas-http4k-templates
```

## Create a function based on an OpenFaaS template

Templates included in this repository:

- `java11-kotlin-http4k`

```
    faas new --lang <template-name> <project-name>
    faas build -f ./<project-name>.yml
```

## Deploy a function

(Optional) if using an OpenFaaS cluster, first deploy to a registry:

```
    faas push -f ./<project-name>.yml
```

Assuming OpenFaas is already deployed:

```
    faas deploy -f ./<project-name>.yml
```
