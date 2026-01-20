# Core Components (Individual Contribution)
## 1. Custom Linked List ADT

This system is built on a custom generic doubly linked list implemented without using the Java Collections Framework. The Linked List serves as the primary Abstract Data Type (ADT) for managing jobs, applicants, matches, and reports across the system.

Key characteristics:
- Doubly linked structure supporting forward and backward traversal
- Efficient insertion and removal at both head and tail
- Position-based access optimized by choosing traversal from head or tail
- Generic design for reuse across different entity types
- To support ordered data, the list provides sorted insertion using Comparable<T>. After insertion, lightweight skip pointers are rebuilt, enabling faster traversal during searches while preserving linked list simplicity.

A flexible find operation accepts a Comparator<T>, allowing customized search logic without coupling the data structure to specific entity attributes. This makes the ADT suitable for multiple search and matching scenarios within the system.

This custom implementation was chosen to maintain full control over traversal, memory links, and optimization strategies, while clearly demonstrating core data structure concepts.

---

## 2. Matching Algorithm (Rule-Based Scoring Engine)

The matching engine evaluates how well an applicant fits a selected job using a weighted, rule-based scoring model. All matching logic operates on the custom Linked List ADT, avoiding built-in collections.

Scoring criteria:
- Skills (60%)
Matches required skills with applicant skills, considering proficiency levels and priority weights.
- Experience (30%)
Compares applicant working experience against job requirements.
- Location (10%)
Prioritises exact district matches, then same-state matches.

Each criterion produces a normalized score, which is combined into a final match percentage (0–100). Scores are capped and rounded for consistency.

Applicants with meaningful match scores are converted into `Application` records and inserted in sorted order based on match score, allowing higher-quality matches to be processed first.

This design keeps the matching logic transparent, adjustable, and easy to extend with additional criteria in the future.

---

## 3. Search Algorithms (Criteria-Based Lookup)

The system provides flexible and optimized search functionality across jobs, applications, skills, and locations, built entirely on the custom Linked List ADT.

Supported search features:
- ID-based search with smart numeric ID matching (e.g. partial or prefix-insensitive input)
- Keyword search across multiple job fields
- Skill and location search with fuzzy matching using Levenshtein distance
- Exact and non-exact (partial) matching modes

For ordered datasets, comparator-driven searches leverage the Linked List’s optimized traversal and skip pointers. When optimized lookup is not possible, the system safely falls back to linear traversal to guarantee correctness.

This layered approach balances performance, flexibility, and robustness without relying on external libraries or collection frameworks.
