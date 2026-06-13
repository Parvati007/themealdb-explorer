import { useEffect, useState } from "react";
import { useParams, Link } from "react-router-dom";
import API from "../api/mealApi";

// TheMealDB stores ingredients across strIngredient1..20 / strMeasure1..20.
function extractIngredients(meal) {
  const items = [];
  for (let i = 1; i <= 20; i++) {
    const ingredient = meal[`strIngredient${i}`];
    const measure = meal[`strMeasure${i}`];
    if (ingredient && ingredient.trim()) {
      items.push(`${ingredient}${measure?.trim() ? ` — ${measure.trim()}` : ""}`);
    }
  }
  return items;
}

// Convert a watch/share URL into an embeddable one.
function toEmbedUrl(youtubeUrl) {
  if (!youtubeUrl) return null;
  const match = youtubeUrl.match(/[?&]v=([^&]+)/) || youtubeUrl.match(/youtu\.be\/([^?&]+)/);
  return match ? `https://www.youtube.com/embed/${match[1]}` : null;
}

function MealDetails() {
  const { id } = useParams();
  const [meal, setMeal] = useState(null);
  const [error, setError] = useState(false);

  useEffect(() => {
    const fetchMeal = async () => {
      try {
        const res = await API.get(`/${id}`);
        setMeal(res.data.meals?.[0] ?? null);
      } catch (err) {
        console.log(err);
        setError(true);
      }
    };

    fetchMeal();
  }, [id]);

  if (error) return <h3 className="container mt-4">Meal not found.</h3>;
  if (!meal) return <h3 className="container mt-4">Loading...</h3>;

  const ingredients = extractIngredients(meal);
  const embedUrl = toEmbedUrl(meal.strYoutube);

  return (
    <div className="container mt-4 mb-5">
      <Link to="/" className="btn btn-outline-secondary mb-3">
        ← Back
      </Link>

      <h2 className="mb-3">{meal.strMeal}</h2>

      <div className="row">
        <div className="col-md-5 mb-3">
          <img
            src={meal.strMealThumb}
            alt={meal.strMeal}
            className="img-fluid rounded"
          />
        </div>

        <div className="col-md-7 mb-3">
          <h4>Ingredients</h4>
          <ul>
            {ingredients.map((item, idx) => (
              <li key={idx}>{item}</li>
            ))}
          </ul>
        </div>
      </div>

      <h4>Instructions</h4>
      <p style={{ whiteSpace: "pre-line" }}>{meal.strInstructions}</p>

      {embedUrl && (
        <>
          <h4>Video</h4>
          <div className="ratio ratio-16x9 mb-3">
            <iframe
              src={embedUrl}
              title={meal.strMeal}
              allowFullScreen
            />
          </div>
        </>
      )}
    </div>
  );
}

export default MealDetails;
