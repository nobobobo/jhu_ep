# Check __file__ is defined
print(__file__)

# Create an unstructured cell types in order to check this script is being run fully
UnstructuredCellTypes()
repr = Show()
Render()
ColorBy(repr, ("POINTS", "Polynomial"))  
