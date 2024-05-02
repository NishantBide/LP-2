cars = {
    "Sedan": ["Toyota Camry", "Honda Accord", "Nissan Altima"],
    "SUV": ["Toyota RAV4", "Honda CR-V", "Ford Explorer"],
    "Truck": ["Ford F-150", "Chevrolet Silverado", "Ram 1500"]
}

def recommend_car(type):
    type_cars = []
    for category, models in cars.items():
        if type.lower() in category.lower():
            type_cars.extend(models)
    if type_cars:
        return type_cars
    else:
        return "Sorry, I don't have recommendations for that car type."

# Example usage:
print("Welcome to the Car Recommendation Chatbot!")
while True:
    user_input = input("Please enter a type of car you're interested in (or 'quit' to exit): ")
    if user_input.lower() == 'quit':
        print("Goodbye!")
        break
    recommendations = recommend_car(user_input)
    if isinstance(recommendations, str):
        print(recommendations)
    else:
        print("Here are some car recommendations for you:")
        for car_model in recommendations:
            print(car_model)
            # print(f"- {car_model}")
