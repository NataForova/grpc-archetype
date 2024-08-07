# archetype grpc

Follow next steps for creating project from this grpc-archetype:
```
1. mvn clean install
```
for generate your new project ``` your_new_project_name ``` use

```
cd your_new_project/your_new_project_name

mvn archetype:generate \
  -DarchetypeGroupId=org.greekleanersinc \
  -DarchetypeArtifactId=grpc-archetype \
  -DarchetypeVersion=0.0.1-SNAPSHOT \
  -DgroupId=org.greekleanersinc \
  -DartifactId=your_new_project_name \
  -Dversion=1.0-SNAPSHOT \
  -DinteractiveMode=false
```


