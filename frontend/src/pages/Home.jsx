import { useEffect, useState } from "react";
import API from "../api/mealApi";

import Navbar from "../components/Navbar";
import SearchBar from "../components/SearchBar";
import MealCard from "../components/MealCard";
import CategoryList from "../components/CategoryList";

function Home() {
  const [meals, setMeals] = useState([]);
  const [categories, setCategories] = useState([]);
  const [search, setSearch] = useState("");



  useEffect(() => {
    const fetchCategories = async () => {
      try {
        const res = await API.get("/categories");

        const names = (res.data.categories || []).map(
          (c) => c.strCategory
        );

        setCategories(names);
      } catch (error) {
        console.log(error);
      }
    };

    fetchCategories();
  }, []);

  const handleSearch = async () => {
    if (!search.trim()) return;

    try {
      const res = await API.get("/search", {
        params: { name: search },
      });

      setMeals(res.data.meals || []);
    } catch (error) {
      console.log(error);
    }
  };

  const fetchCategoryMeals = async (name) => {
    try {
      const res = await API.get(`/category/${name}`);

      setMeals(res.data.meals || []);
    } catch (error) {
      console.log(error);
    }
  };

  const randomMeal = async () => {
    try {
      const res = await API.get("/random");

      setMeals(res.data.meals || []);
    } catch (error) {
      console.log(error);
    }
  };

  return (
    <>
      <Navbar />

      <div className="container mt-4">

        <SearchBar
          search={search}
          setSearch={setSearch}
          handleSearch={handleSearch}
        />

        <button
          className="btn btn-success mb-4"
          onClick={randomMeal}
        >
          I'm Feeling Hungry 🍔
        </button>

        <CategoryList
          categories={categories}
          fetchCategoryMeals={fetchCategoryMeals}
        />

        <div className="row">
          {meals.map((meal) => (
            <MealCard
              key={meal.idMeal}
              meal={meal}
            />
          ))}
        </div>

      </div>
    </>
  );
}

export default Home;