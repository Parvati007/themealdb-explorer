function CategoryList({ categories, fetchCategoryMeals }) {
  return (
    <div className="mb-4">
      {categories.map((cat) => (
        <button
          key={cat}// Unique key for efficient React rendering
          className="btn btn-outline-primary me-2 mb-2"
          onClick={() => fetchCategoryMeals(cat)}// Fetch meals for selected category
        >
          {cat}
        </button>
      ))}
    </div>
  );
}

export default CategoryList;