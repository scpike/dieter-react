// provide a global exports for JSXTransformer to attach itself to
var global = this;

function formatError(e, filename) {
  return filename + ": " + e.message;
}

function compileJSX(input, absolutePath, filename){
  try {
    var x = JSXTransformer.transform(input);
    return(x.code);
  } catch (x) {
    return formatError(x, filename);
  }
};
